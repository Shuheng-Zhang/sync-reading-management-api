package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.sync.reading.management.api.entity.BookMetaInfo;

public interface BookMetaInfoMapper extends BaseMapper<BookMetaInfo> {

    /**
     * 删除书目元数据
     * @param bookId 目标书目ID
     * @return
     */
    int deleteByBookId(String bookId);
}