package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.sync.reading.management.api.entity.ReadingProgressInfo;

public interface ReadingProgressInfoMapper extends BaseMapper<ReadingProgressInfo> {

    /**
     * 删除阅读记录
     * @param bookId 书目ID
     * @return
     */
    int deleteByBookId(String bookId);
}