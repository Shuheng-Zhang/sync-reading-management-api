package com.heng.sync.reading.management.api.commons.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页查询参数对象
 */
@Data
@AllArgsConstructor
public class PageQuery {

    /**
     * 默认当前页码
     */
    final private static Integer DEFAULT_CUR_PAGE = 1;
    /**
     * 默认单页容量
     */
    final private static Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页码
     */
    private Integer curPage;
    /**
     * 单页记录容量
     */
    private Integer sizePerPage;

    public PageQuery() {
        this.curPage = DEFAULT_CUR_PAGE;
        this.sizePerPage = DEFAULT_PAGE_SIZE;
    }
}
