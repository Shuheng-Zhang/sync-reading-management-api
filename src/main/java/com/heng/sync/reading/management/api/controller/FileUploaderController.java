package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.heng.sync.reading.management.api.commons.enums.FileTypeEnum;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.book.EpubBookProcessingDto;
import com.heng.sync.reading.management.api.service.process.EpubBookProcessingService;
import com.heng.sync.reading.management.api.service.process.FileProcessingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SaCheckLogin
@SaCheckRole(value = "app-reader")
@RestController
@RequestMapping(value = "/upload")
public class FileUploaderController {

    @Value("${appConfig.dataStorage.dataDirRoot}")
    private String dataRootDir;

    @Value("${appConfig.dataStorage.uploadedTmpPrefix}")
    private String tmpPrefix;

    @Resource
    private FileProcessingService fileProcessingService;

    @Resource
    private EpubBookProcessingService epubBookProcessingService;

    /**
     * 上传 ePub 类型文件
     * @param files 目标文件列表
     * @return
     */
    @PostMapping(value = "epub")
    public DataResult epubUploadFiles(MultipartFile[] files) {

        if (files == null) {
            return DataResult.error(RespEnum.PARAMS_INVALID, null);
        }

        String accountId = (String) StpUtil.getLoginId();

        List<EpubBookProcessingDto> processingDtoList = new ArrayList<>();
        for (MultipartFile file : files) {
            String originFileName = file.getOriginalFilename();
            String storedFileName = fileProcessingService.transferTo(file, dataRootDir + tmpPrefix, FileTypeEnum.EPUB, FileTypeEnum.EPUB_ZIP);

            processingDtoList.add(new EpubBookProcessingDto(accountId, originFileName, dataRootDir + tmpPrefix + "/" + storedFileName));
        }

        epubBookProcessingService.analysisBookResources(processingDtoList);

        return DataResult.success("BOOKS_ANALYSING");
    }
}
