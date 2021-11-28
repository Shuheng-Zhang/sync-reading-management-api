package com.heng.sync.reading.management.api.commons.executor;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 可视化线程执行器
 */
@Component
public class VisibleThreadPoolExecutor extends ThreadPoolTaskExecutor {

    final private static Log LOGGER = LogFactory.get();

    /**
     * 获取执行器信息
     * @return 执行器信息对象
     */
    public ExecutorInfoDto info() {
        final ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        ExecutorInfoDto executorInfoDto = new ExecutorInfoDto(this.getDefaultThreadNamePrefix(),
                executor.getTaskCount(), executor.getActiveCount(), executor.getCompletedTaskCount(),
                executor.getQueue().size());
        LOGGER.info("====== VisibleThreadPoolExecutor Info ======");
        LOGGER.info("==> Name:          {}", executorInfoDto.getExecutorName());
        LOGGER.info("==> TotalTasks:    {}", executorInfoDto.getTotalTaskCount());
        LOGGER.info("==> Processing:    {}", executorInfoDto.getProcessingTaskCount());
        LOGGER.info("==> Completed:     {}", executorInfoDto.getCompletedTaskCount());
        LOGGER.info("==> Queued:        {}", executorInfoDto.getQueuedTaskCount());
        LOGGER.info("====== VisibleThreadPoolExecutor Info ======");

        return executorInfoDto;
    }
}
