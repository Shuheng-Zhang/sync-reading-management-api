package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.bookmark.BookmarkCreationDto;
import com.heng.sync.reading.management.api.dto.bookmark.BookmarkQueryDto;
import com.heng.sync.reading.management.api.entity.BookmarkInfo;
import com.heng.sync.reading.management.api.service.data.BookmarkInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@SaCheckLogin
@SaCheckRole(value = "app-reader")
@RestController
@RequestMapping(value = "/bookmark")
public class BookmarkController {

    @Resource
    private BookmarkInfoService bookmarkInfoService;

    @PostMapping(value = "creation")
    public DataResult createBookmark(@RequestBody BookmarkCreationDto creationDto) {
        bookmarkInfoService.createBookmark(creationDto);
        return DataResult.success();
    }

    @PostMapping(value = "list")
    public DataResult findBookmarks(@RequestBody BookmarkQueryDto queryDto) {
        IPage<BookmarkInfo> retPage = bookmarkInfoService.findByBookId(queryDto);

        return DataResult.success(retPage);
    }

    @PostMapping(value = "purge")
    public DataResult purgeBookmarks(@RequestBody List<String> bookmarkIds) {
        bookmarkInfoService.purgeByBookmarkIds(bookmarkIds);

        return DataResult.success();
    }
}
