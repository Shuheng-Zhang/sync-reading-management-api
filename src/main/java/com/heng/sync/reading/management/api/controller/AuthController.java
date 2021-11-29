package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.account.AccountAuthDto;
import com.heng.sync.reading.management.api.dto.account.AccountLoginDoneDto;
import com.heng.sync.reading.management.api.service.process.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * 用户登入
     * @param authDto 登入信息
     * @return 用户 Token
     */
    @PostMapping(value = "login")
    public DataResult login(@RequestBody AccountAuthDto authDto) {
        AccountLoginDoneDto loginDoneDto = authService.auth(authDto);
        return DataResult.success(loginDoneDto);
    }

    /**
     * 用户登出
     * @return
     */
    @SaCheckLogin
    @GetMapping(value = "logout")
    public DataResult logout() {
        String accountId = (String) StpUtil.getLoginId();
        authService.logout(accountId);
        return DataResult.success();
    }
}
