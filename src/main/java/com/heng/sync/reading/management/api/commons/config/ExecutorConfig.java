package com.heng.sync.reading.management.api.commons.config;

import com.heng.sync.reading.management.api.commons.executor.VisibleThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ExecutorConfig {

    @Value("${appConfig.asyncServiceExecutor.corePoolSize}")
    private int corePoolSize;
    @Value("${appConfig.asyncServiceExecutor.maxPoolSize}")
    private int maxPoolSize;
    @Value("${appConfig.asyncServiceExecutor.queueCapacity}")
    private int queueCapacity;
    @Value("${appConfig.asyncServiceExecutor.keepAliveSeconds}")
    private int keepAliveSeconds;
    @Value("${appConfig.asyncServiceExecutor.namePrefix}")
    private String namePrefix;

    @Bean("asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        VisibleThreadPoolExecutor executor = new VisibleThreadPoolExecutor();

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(namePrefix);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();

        return executor;
    }
}
