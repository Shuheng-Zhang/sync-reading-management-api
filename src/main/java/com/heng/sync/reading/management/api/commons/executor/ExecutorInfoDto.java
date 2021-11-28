package com.heng.sync.reading.management.api.commons.executor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 执行器信息对象
 */
@Data
@AllArgsConstructor
public class ExecutorInfoDto {

    /**
     * 执行器名称
     */
    private String executorName;
    /**
     * 任务总数
     */
    private long totalTaskCount;
    /**
     * 进行中的任务数量
     */
    private long processingTaskCount;
    /**
     * 已完成的任务数量
     */
    private long completedTaskCount;
    /**
     * 等待队列中的任务数量
     */
    private long queuedTaskCount;
}
