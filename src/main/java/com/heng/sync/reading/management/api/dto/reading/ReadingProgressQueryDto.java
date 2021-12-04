package com.heng.sync.reading.management.api.dto.reading;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 阅读进度查询数据对象
 */
@Data
@AllArgsConstructor
public class ReadingProgressQueryDto {
    /**
     * 目标书目ID
     */
    private String bookId;
}
