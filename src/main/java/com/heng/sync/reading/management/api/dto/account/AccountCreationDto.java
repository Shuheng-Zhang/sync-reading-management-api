package com.heng.sync.reading.management.api.dto.account;

import lombok.Data;

/**
 * 数据对象 - 创建新用户账号
 */
@Data
public class AccountCreationDto {

    /**
     * 用户账号名
     */
    private String accountName;
    /**
     * 用户账号口令
     */
    private String accountPasswd;
    /**
     * 用户账号类型
     */
    private Integer accountType;
}
