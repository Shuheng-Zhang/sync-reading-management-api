package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.book.EpubBookQueryDto;
import com.heng.sync.reading.management.api.service.data.EpubBookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@SaCheckLogin
@SaCheckRole(value = "app-reader")
@RestController
@RequestMapping(value = "/epub")
public class EpubBookController {

    @Resource
    private EpubBookService epubBookService;

    @PostMapping(value = "listWithBaseInfo")
    public DataResult listBooksWithBaseInfoByPage(@RequestBody EpubBookQueryDto queryDto) {
        return DataResult.success(epubBookService.findBooksWithBaseInfo(queryDto));
    }

    @PostMapping(value = "listWithDetailInfo")
    public DataResult listBookWithDetailInfoByPage(@RequestBody EpubBookQueryDto queryDto) {
        return DataResult.success(epubBookService.findBooksWithDetailInfo(queryDto));
    }

    @PostMapping(value = "listWithAnalysisInfo")
    public DataResult listBookWithAnalysisInfoByPage(@RequestBody EpubBookQueryDto queryDto) {
        return DataResult.success(epubBookService.findBooksWithAnalysisInfo(queryDto));
    }

    @PostMapping(value = "remove")
    public DataResult removeBooks(@RequestBody List<String> bookIds) {
        epubBookService.removeBooks(bookIds);

        return DataResult.success();
    }

    @PostMapping(value = "purge")
    public DataResult purgeBooks(@RequestBody List<String> bookIds) {
        epubBookService.purgeBooks(bookIds);
        return DataResult.success();
    }
}
