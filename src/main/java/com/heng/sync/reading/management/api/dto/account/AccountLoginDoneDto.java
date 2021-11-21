package com.heng.sync.reading.management.api.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据对象 - 账号登入完成
 */
@Data
@AllArgsConstructor
public class AccountLoginDoneDto {

    /**
     * 用户ID
     */
    private String accountId;
    /**
     * 用户名
     */
    private String accountName;
    /**
     * Token
     */
    private String token;
}
