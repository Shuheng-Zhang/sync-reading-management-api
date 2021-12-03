package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.heng.sync.reading.management.api.dto.book.EpubBookQueryDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultAnalysisDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultBaseDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultDetailDto;
import org.apache.ibatis.annotations.Param;

public interface BookQueryMapper {

    /**
     * 查询书目基本信息
     * @param page 分页控制
     * @param queryDto 查询条件对象
     * @return
     */
    IPage<QueryResultBaseDto> queryBooksWithBaseInfo(IPage<QueryResultBaseDto> page,
                                                     @Param("queryDto") EpubBookQueryDto queryDto);
    /**
     * 查询书目详细信息
     * @param page 分页控制
     * @param queryDto 查询条件对象
     * @return
     */
    IPage<QueryResultDetailDto> queryBooksWithDetailInfo(IPage<QueryResultDetailDto> page,
                                                         @Param("queryDto") EpubBookQueryDto queryDto);

    /**
     * 查询书目统计信息
     * @param page 分页控制
     * @param queryDto 查询条件对象
     * @return
     */
    IPage<QueryResultAnalysisDto> queryBooksWithAnalysisInfo(IPage<QueryResultAnalysisDto> page,
                                                             @Param("queryDto") EpubBookQueryDto queryDto);
}
