package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.sync.reading.management.api.entity.BookResourceInfo;

public interface BookResourceInfoMapper extends BaseMapper<BookResourceInfo> {

    /**
     * 查询书目资源信息
     * @param bookId 目标书目ID
     * @return
     */
    BookResourceInfo queryByBookId(String bookId);

    /**
     * 查询书目是否存在
     * @param bookId 目标书目ID
     * @return
     */
    Integer queryExistedByBookId(String bookId);

    /**
     * 删除书目资源记录
     * @param bookId 目标书目ID
     * @return
     */
    int deleteByBookId(String bookId);
}