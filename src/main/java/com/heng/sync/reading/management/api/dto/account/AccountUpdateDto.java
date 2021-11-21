package com.heng.sync.reading.management.api.dto.account;

import lombok.Data;

/**
 * 数据对象 - 更新用户账号
 */
@Data
public class AccountUpdateDto {

    /**
     * 用户账号ID
     */
    private String accountId;
    /**
     * 用户账号口令
     */
    private String accountPasswd;
    /**
     * 锁定状态
     */
    private Integer locked;
}
