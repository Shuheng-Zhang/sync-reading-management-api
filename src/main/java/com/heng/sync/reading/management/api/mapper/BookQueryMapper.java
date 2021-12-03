package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.heng.sync.reading.management.api.dto.book.QueryResultAnalysisDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultBaseDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultDetailDto;
import org.apache.ibatis.annotations.Param;

public interface BookQueryMapper {

    /**
     * 查询书目基本信息
     * @param page 分页控制
     * @param accountId 用户账号ID
     * @param bookTitleLike 条件查询-书目标题
     * @param bookAuthorsLike 条件查询-作者
     * @return
     */
    IPage<QueryResultBaseDto> queryBooksWithBaseInfo(IPage<QueryResultBaseDto> page,
                                                     @Param("accountId") String accountId,
                                                     @Param("bookTitleLike") String bookTitleLike,
                                                     @Param("bookAuthorsLike") String bookAuthorsLike
                                                     );
    /**
     * 查询书目详细信息
     * @param page 分页控制
     * @param accountId 用户账号ID
     * @param bookTitleLike 条件查询-书目标题
     * @param bookAuthorsLike 条件查询-作者
     * @return
     */
    IPage<QueryResultDetailDto> queryBooksWithDetailInfo(IPage<QueryResultDetailDto> page,
                                                         @Param("accountId") String accountId,
                                                         @Param("bookTitleLike") String bookTitleLike,
                                                         @Param("bookAuthorsLike") String bookAuthorsLike);

    /**
     * 查询书目统计信息
     * @param page 分页控制
     * @param accountId 用户账号ID
     * @param bookTitleLike 条件查询-书目标题
     * @param bookAuthorsLike 条件查询-作者
     * @return
     */
    IPage<QueryResultAnalysisDto> queryBooksWithAnalysisInfo(IPage<QueryResultAnalysisDto> page,
                                                             @Param("accountId") String accountId,
                                                             @Param("bookTitleLike") String bookTitleLike,
                                                             @Param("bookAuthorsLike") String bookAuthorsLike);
}
