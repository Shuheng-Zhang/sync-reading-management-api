package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.config.AppGlobalConfigOptDto;
import com.heng.sync.reading.management.api.dto.config.AppGlobalConfigQueryDto;
import com.heng.sync.reading.management.api.entity.AppGlobalConfigInfo;
import com.heng.sync.reading.management.api.service.data.AppGlobalConfigInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SaCheckLogin
@SaCheckRole(value = "app-reader")
@RestController
@RequestMapping(value = "appGlobalConfig")
public class AppGlobalConfigInfoController {

    @Resource
    private AppGlobalConfigInfoService appGlobalConfigInfoService;

    @PostMapping(value = "list")
    public DataResult listGlobalConfig(@RequestBody AppGlobalConfigQueryDto queryDto) {
        AppGlobalConfigInfo appGlobalConfigInfo = appGlobalConfigInfoService.findByAccountId(queryDto);
        return DataResult.success(appGlobalConfigInfo);
    }

    @PostMapping(value = "operation")
    public DataResult operateGlobalConfigInfo(@RequestBody AppGlobalConfigOptDto optDto) {
        appGlobalConfigInfoService.createOrUpdateAppGlobalConfig(optDto);
        return DataResult.success();
    }

    @PostMapping(value = "purge")
    public DataResult deleteGlobalConfigInfo(@RequestBody AppGlobalConfigQueryDto queryDto) {
        appGlobalConfigInfoService.deleteAppGlobalConfigInfo(queryDto);
        return DataResult.success();
    }
}
