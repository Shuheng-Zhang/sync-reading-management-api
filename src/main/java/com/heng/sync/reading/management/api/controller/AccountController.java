package com.heng.sync.reading.management.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import com.heng.sync.reading.management.api.dto.account.AccountCreationDto;
import com.heng.sync.reading.management.api.dto.account.AccountUpdateDto;
import com.heng.sync.reading.management.api.service.data.AccountInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SaCheckLogin
@SaCheckRole(value = "app-admin")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Resource
    private AccountInfoService accountInfoService;

    @SaCheckLogin
    @PostMapping(value = "creation")
    public DataResult createAccount(@RequestBody AccountCreationDto accountCreationDto) {
        this.accountInfoService.createAccount(accountCreationDto);
        return DataResult.success();
    }

    @SaCheckLogin
    @PostMapping(value = "update")
    public DataResult updateAccount(@RequestBody AccountUpdateDto accountUpdateDto) {
        this.accountInfoService.updateAccountInfo(accountUpdateDto);
        return DataResult.success();
    }
}
