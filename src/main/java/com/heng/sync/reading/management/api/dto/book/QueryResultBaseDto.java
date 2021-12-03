package com.heng.sync.reading.management.api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ePub 电子书查询结果 - 基本信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResultBaseDto {

    /**
     * 书目ID
     */
    private String id;
    /**
     * 书目标题
     */
    private String bookTitle;
    /**
     * 书目源文件名称
     */
    private String bookOriginFileName;
    /**
     * 书目作者
     */
    private String bookAuthors;

    /**
     * 书目资源URL
     */
    private String bookResourceUrl;

    /**
     * 书目封面URL
     */
    private String bookCoverUrl;
}
