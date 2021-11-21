package com.heng.sync.reading.management.api.dto.account;

import lombok.Data;

/**
 * 数据对象 - 用户验证对象
 */
@Data
public class AccountAuthDto {

    /**
     * 账号用户名
     */
    private String accountName;

    /**
     * 账号验证口令
     */
    private String accountPasswd;
}
