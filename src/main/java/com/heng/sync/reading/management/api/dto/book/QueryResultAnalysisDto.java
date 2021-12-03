package com.heng.sync.reading.management.api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ePub 电子书查询结果-存储统计
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryResultAnalysisDto extends QueryResultBaseDto {

    /**
     * 书目导入时间
     */
    private Date bookPushedTime;
    /**
     * 书目资源容量
     */
    private Integer bookResourceSize;
}
