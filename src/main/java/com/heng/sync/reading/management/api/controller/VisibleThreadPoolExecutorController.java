package com.heng.sync.reading.management.api.controller;

import com.heng.sync.reading.management.api.commons.executor.VisibleThreadPoolExecutor;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/visibleExecutor")
public class VisibleThreadPoolExecutorController {

    @Resource
    private VisibleThreadPoolExecutor visibleThreadPoolExecutor;

    /**
     * 查询线程执行器信息
     * @return
     */
    @GetMapping(value = "info")
    public DataResult visibleThreadPoolExecutorInfo() {
        return DataResult.success(visibleThreadPoolExecutor.info());
    }
}
