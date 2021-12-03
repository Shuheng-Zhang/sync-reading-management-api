package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.sync.reading.management.api.entity.BookmarkInfo;

public interface BookmarkInfoMapper extends BaseMapper<BookmarkInfo> {

    /**
     * 删除书目书签记录
     * @param bookId 目标书目ID
     * @return
     */
    int deleteByBookId(String bookId);
}