package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.reading.ReadingProgressInfoOptDto;
import com.heng.sync.reading.management.api.dto.reading.ReadingProgressQueryDto;
import com.heng.sync.reading.management.api.entity.ReadingProgressInfo;
import com.heng.sync.reading.management.api.service.data.ReadingProgressInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SaCheckLogin
@SaCheckRole(value = "app-reader")
@RestController
@RequestMapping(value = "/readingProgress")
public class ReadingProgressInfoController {

    @Resource
    private ReadingProgressInfoService readingProgressInfoService;

    @PostMapping(value = "list")
    public DataResult listReadingProgressInfo(@RequestBody ReadingProgressQueryDto queryDto) {
        ReadingProgressInfo readingProgressInfo = readingProgressInfoService.findByBookId(queryDto);

        return DataResult.success(readingProgressInfo);
    }


    @PostMapping(value = "operation")
    public DataResult createOrUpdateReadingProgressInfo(@RequestBody ReadingProgressInfoOptDto optDto) {
        readingProgressInfoService.createOrUpdateReadingProgress(optDto);
        return DataResult.success();
    }

    @PostMapping(value = "purge")
    public DataResult purgeReadingProgressInfo(@RequestBody ReadingProgressQueryDto queryDto) {
        readingProgressInfoService.deleteByBookId(queryDto.getBookId());
        return DataResult.success();
    }
}

