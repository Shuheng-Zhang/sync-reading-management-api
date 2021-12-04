package com.heng.sync.reading.management.api.service.data;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.dto.book.EpubBookQueryDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultAnalysisDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultBaseDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultDetailDto;
import com.heng.sync.reading.management.api.entity.BookResourceInfo;
import com.heng.sync.reading.management.api.mapper.*;
import com.heng.sync.reading.management.api.service.process.FileProcessingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EpubBookService {

    @Value("${appConfig.dataStorage.dataDirRoot}")
    private String dataRootDir;

    @Value("${appConfig.dataStorage.epubPrefix}")
    private String epubPrefix;

    @Resource
    private BookQueryMapper bookQueryMapper;

    @Resource
    private BookResourceInfoMapper bookResourceInfoMapper;

    @Resource
    private BookMetaInfoMapper bookMetaInfoMapper;

    @Resource
    private BookmarkInfoMapper bookmarkInfoMapper;

    @Resource
    private ReadingProgressInfoMapper readingProgressInfoMapper;

    @Resource
    private FileProcessingService fileProcessingService;

    /**
     * 查询书目基本信息
     * @param queryDto 查询对象
     * @return
     */
    public IPage<QueryResultBaseDto> findBooksWithBaseInfo(EpubBookQueryDto queryDto) {

        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithBaseInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), queryDto);
    }

    /**
     * 查询书目详细信息
     * @param queryDto 查询对象
     * @return
     */
    public IPage<QueryResultDetailDto> findBooksWithDetailInfo(EpubBookQueryDto queryDto) {
        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithDetailInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), queryDto);
    }

    /**
     * 查询书目统计信息
     * @param queryDto 查询对象
     * @return
     */
    public IPage<QueryResultAnalysisDto> findBooksWithAnalysisInfo(EpubBookQueryDto queryDto) {
        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithAnalysisInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), queryDto);
    }

    /**
     * 移除电子书到回收站
     * @param bookIds 目标书目ID列表
     */
    @Transactional
    public void removeBooks(List<String> bookIds) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }
        bookResourceInfoMapper.deleteBatchIds(bookIds);
    }

    /**
     * 彻底删除书目资源及所有相关记录
     * 异步执行
     * @param bookIds 目标书目ID列表
     */
    @Async(value = "asyncServiceExecutor")
    @Transactional
    public void purgeBooks(List<String> bookIds) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        for (String bookId : bookIds) {

            readingProgressInfoMapper.deleteByBookId(bookId);

            bookMetaInfoMapper.deleteByBookId(bookId);

            bookmarkInfoMapper.deleteByBookId(bookId);

            String targetAccountId = null;
            String resourceDir = null;
            BookResourceInfo targetResourceInfo = bookResourceInfoMapper.queryByBookId(bookId);
            if (targetResourceInfo != null) {
                resourceDir = targetResourceInfo.getBookResourceUrl();
                targetAccountId = targetResourceInfo.getAccountId();

                bookResourceInfoMapper.deleteByBookId(bookId);
            }

            String targetDirPath = dataRootDir + "/" + targetAccountId + epubPrefix + "/" + resourceDir;
            fileProcessingService.cleanupFileOrDir(targetDirPath);
        }
    }
}
