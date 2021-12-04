package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.sync.reading.management.api.dto.bookmark.BookmarkQueryDto;
import com.heng.sync.reading.management.api.entity.BookmarkInfo;
import org.apache.ibatis.annotations.Param;

public interface BookmarkInfoMapper extends BaseMapper<BookmarkInfo> {

    /**
     * 查询目标书目的书签信息
     * @param page 分页控制
     * @param queryDto 查询数据对象
     * @return
     */
    IPage<BookmarkInfo> queryByBookId(Page<BookmarkInfo> page, @Param("queryDto") BookmarkQueryDto queryDto);

    /**
     * 删除书目书签记录
     * @param bookId 目标书目ID
     * @return
     */
    int deleteByBookId(String bookId);
}