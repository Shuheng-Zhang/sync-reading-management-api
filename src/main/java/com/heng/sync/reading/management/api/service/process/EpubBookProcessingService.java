package com.heng.sync.reading.management.api.service.process;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.dto.book.BookProcessingDto;
import com.heng.sync.reading.management.api.entity.BookMetaInfo;
import com.heng.sync.reading.management.api.entity.BookResourceInfo;
import com.heng.sync.reading.management.api.mapper.BookMetaInfoMapper;
import com.heng.sync.reading.management.api.mapper.BookResourceInfoMapper;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

@Service
public class EpubBookProcessingService {

    final private static Log LOGGER = LogFactory.get();

    @Value("${appConfig.dataStorage.dataDirRoot}")
    private String dataDirRoot;

    @Value("${appConfig.dataStorage.epubPrefix}")
    private String epubPrefix;

    @Resource
    private BookResourceInfoMapper bookResourceInfoMapper;

    @Resource
    private BookMetaInfoMapper bookMetaInfoMapper;

    @Resource
    private FileProcessingService fileProcessingService;

    /**
     * 异步服务
     * 解析电子书资源信息
     * @param bookList 书目处理列表
     */
    @Async(value = "asyncServiceExecutor")
    public void analysisBookResources(List<BookProcessingDto> bookList) {
        if (bookList == null || bookList.isEmpty()) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        for (BookProcessingDto processingDto : bookList) {
            LOGGER.info("analysisBookResources ==> Analysing: {}", processingDto);
            try {
                // 解压缩
                String unpackedDirName = processBookUnpacking(processingDto.getAccountId(), processingDto.getBookTmpStoredFilePath(), IdUtil.simpleUUID());
                if (StrUtil.isEmptyIfStr(unpackedDirName)) {
                    throw new BusinessException(RespEnum.SYS_ERR);
                }
                // 解析资源目录
                BookResourceInfo bookResourceInfo = processBookResourceAnalysis(processingDto.getAccountId(), processingDto.getBookOriginFileName(), unpackedDirName);
                if (bookResourceInfo == null) {
                    throw new BusinessException(RespEnum.SYS_ERR);
                }

                // 解析书目元数据
                BookMetaInfo bookMetaInfo = processBookMetaInfoAnalysis(bookResourceInfo.getId(), processingDto.getBookTmpStoredFilePath());
                if (bookMetaInfo == null) {
                    throw new BusinessException(RespEnum.SYS_ERR);
                }

                // 存储解析结果到数据库
                this.bookResourceInfoMapper.insert(bookResourceInfo);
                this.bookMetaInfoMapper.insert(bookMetaInfo);

                fileProcessingService.cleanupFile(processingDto.getBookTmpStoredFilePath());

            } catch (Exception e) {
                throw new BusinessException(RespEnum.SYS_ERR, e.getCause() + ": " + e.getMessage());
            }
            LOGGER.info("analysisBookResources ==> Analysed: {}", processingDto);
            LOGGER.info("analysisBookResources ==> Cleaning...");

        }
    }

    /**
     * 解压缩电子书
     *
     * @param accountId          用户账号ID
     * @param targetBookFilePath 目标书目绝对路径
     * @param unpackedDirName    解压缩目录名称
     * @return 解压缩后目录名称
     */
    private String processBookUnpacking(String accountId, String targetBookFilePath, String unpackedDirName) {

        if (StrUtil.isEmptyIfStr(targetBookFilePath) || StrUtil.isEmptyIfStr(unpackedDirName)) {
            return null;
        }

        if (!FileUtil.exist(targetBookFilePath)) {
            return null;
        }

        File targetFile = new File(targetBookFilePath);
        File destDir = FileUtil.mkdir(dataDirRoot + "/" + accountId + epubPrefix + "/" + unpackedDirName);

        File unpackedDir = ZipUtil.unzip(targetFile, destDir);

        return unpackedDir.getName();
    }

    /**
     * 解析电子书资源信息
     * @param accountId 用户账号ID
     * @param targetBookOriginFileName 电子书源文件名称
     * @param targetBookUnpackedDirName 电子书解压缩目录名称
     * @return
     */
    private BookResourceInfo processBookResourceAnalysis(String accountId, String targetBookOriginFileName, String targetBookUnpackedDirName) {

        if (StrUtil.isEmptyIfStr(targetBookOriginFileName) || StrUtil.isEmptyIfStr(accountId)) {
            return null;
        }

        String targetDirPath = dataDirRoot + "/" + accountId + epubPrefix + "/" + targetBookUnpackedDirName;
        if (!FileUtil.exist(targetDirPath)) {
            return null;
        }

        String resourceId = IdUtil.getSnowflake().nextIdStr();
        int resourceSize = (int) FileUtil.size(new File(targetDirPath));

        return new BookResourceInfo(resourceId, accountId, targetBookOriginFileName, targetBookUnpackedDirName, resourceSize, new Date(), 0);
    }

    /**
     * 解析电子书元数据
     * @param bookId 电子书ID
     * @param targetBookFilePath 目标电子书文件路径
     * @return 电子书元数据信息
     * @throws Exception
     */
    private BookMetaInfo processBookMetaInfoAnalysis(String bookId, String targetBookFilePath) throws Exception {

        if (!FileUtil.exist(targetBookFilePath) || StrUtil.isEmptyIfStr(bookId)) {
            return null;
        }

        EpubReader epubReader = new EpubReader();
        Book targetBook = epubReader.readEpub(new FileInputStream(targetBookFilePath));

        String bookCoverUrl = targetBook.getCoverImage().getHref();
        String bookOpfUrl = targetBook.getOpfResource().getHref();
        int contentsSize = targetBook.getTableOfContents().size();

        Metadata metadata = targetBook.getMetadata();
        String bookTitle = metadata.getFirstTitle();
        String bookAuthors = this.processBookAuthorsInfo(metadata.getAuthors());
        String bookDescription = this.processBookDescriptionInfo(metadata.getDescriptions());

        return new BookMetaInfo(null, bookId, bookTitle, bookAuthors, bookDescription, contentsSize, bookCoverUrl, bookOpfUrl, 0);
    }

    /**
     * 处理电子书作者列表信息
     *
     * @param authorList 作者列表
     * @return 作者列表信息
     */
    private String processBookAuthorsInfo(List<Author> authorList) {
        if (authorList == null || authorList.isEmpty()) {
            return "UNKNOWN_AUTHOR";
        }

        StringBuilder authorsBuilder = new StringBuilder();
        authorList.forEach(author -> {
            String firstName = author.getFirstname();
            String lastName = author.getLastname();
            if (!firstName.equals(lastName)) {
                authorsBuilder.append(lastName).append(" ").append(firstName);
            } else {
                authorsBuilder.append(lastName);
            }
            authorsBuilder.append(",");
        });

        int lastCotIndex = authorsBuilder.lastIndexOf(",");
        if (lastCotIndex == authorsBuilder.length() - 1) {
            authorsBuilder.delete(lastCotIndex, authorsBuilder.length());
        }
        return authorsBuilder.toString();
    }

    /**
     * 处理电子书简介信息
     *
     * @param descriptionList 简介信息列表
     * @return 简介信息
     */
    private String processBookDescriptionInfo(List<String> descriptionList) {
        if (descriptionList == null || descriptionList.isEmpty()) {
            return "NO_DESCRIPTION";
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionList.forEach(descriptionBuilder::append);
        return descriptionBuilder.toString();
    }
}
