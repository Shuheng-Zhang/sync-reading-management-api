package com.heng.sync.reading.management.api.dto.reading;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 阅读进度操作数据对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgressInfoOptDto {

    /**
     * 目标书目ID
     */
    private String bookId;
    /**
     * 当前阅读章节名称
     */
    private String curChapterName;
    /**
     * 当前阅读进度百分比
     */
    private BigDecimal curReadPercentage;
    /**
     * 当前阅读文本定位符
     */
    private String curReadLocation;
}
