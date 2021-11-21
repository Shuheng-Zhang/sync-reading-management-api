package com.heng.sync.reading.management.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户账号信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "account_info")
public class AccountInfo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户名
     */
    @TableField(value = "account_name")
    private String accountName;

    /**
     * 用户验证凭据
     */
    @TableField(value = "account_cert")
    private String accountCert;

    /**
     * 用户安全密钥
     */
    @TableField(value = "account_security_key")
    private String accountSecurityKey;

    /**
     * 用户类型, 0-阅读者; 1-管理员
     */
    @TableField(value = "account_type")
    private Integer accountType;

    /**
     * 锁定状态标识, 0-正常; 1-锁定
     */
    @TableField(value = "is_locked")
    private Integer locked;

    public static final String COL_ID = "id";

    public static final String COL_ACCOUNT_NAME = "account_name";

    public static final String COL_ACCOUNT_CERT = "account_cert";

    public static final String COL_ACCOUNT_SECURITY_KEY = "account_security_key";

    public static final String COL_ACCOUNT_TYPE = "account_type";

    public static final String COL_IS_LOCKED = "is_locked";
}