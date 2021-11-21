package com.heng.sync.reading.management.api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据对象 - 书目分析元数据
 */
@Data
@AllArgsConstructor
public class BookAnalysisDto {

    /**
     * 书目ID
     */
    private String bookId;

    /**
     * 源书目文件名称
     */
    private String bookOriginFileName;

    /**
     * 源书目文件路径
     * 该书目文件存储于服务器文件系统中的路径
     */
    private String bookOriginFilePath;
}
