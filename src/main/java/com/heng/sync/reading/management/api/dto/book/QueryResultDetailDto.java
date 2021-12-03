package com.heng.sync.reading.management.api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ePub 电子书查询结果 - 详细信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResultDetailDto extends QueryResultBaseDto {

    /**
     * 书目 OPF 文档URL
     */
    private String bookOpfUrl;
    /**
     * 书目简介
     */
    private String bookDescription;
    /**
     * 书目章节统计
     */
    private Integer contentsCount;
}
