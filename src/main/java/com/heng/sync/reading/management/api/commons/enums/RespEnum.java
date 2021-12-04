package com.heng.sync.reading.management.api.commons.enums;

/**
 * 响应信息枚举
 */
public enum RespEnum {

    /**
     * 响应正常
     */
    OK(200, "OK"),
    /**
     * 参数错误
     */
    PARAMS_INVALID(400, "PARAMS_INVALID"),

    /**
     * 资源不存在
     */
    RESOURCE_NOT_FOUND(400, "RESOURCE_NOT_FOUND"),

    /**
     * 文件类型不被支持
     */
    FILE_TYPE_UNSUPPORTED(400, "FILE_TYPE_UNSUPPORTED"),
    /**
     * 用户名已被使用
     */
    ACCOUNT_NAME_ALREADY_USED(400, "ACCOUNT_NAME_ALREADY_USED"),
    /**
     * 用户名或用户凭证不正确
     */
    USERNAME_OR_CERT_INVALID(401, "USERNAME_OR_CERT_INVALID"),
    /**
     * 用户信息不正确
     */
    USER_INFO_INVALID(401, "USER_INFO_INVALID"),
    /**
     * 用户被锁定
     */
    ACCOUNT_LOCKED(401, "ACCOUNT_HAS_BEEN_LOCKED"),
    /**
     * 无权限
     */
    PERMISSION_DENIED(401, "PERMISSION_DENIED"),
    /**
     * Token 无效
     */
    TOKEN_INVALID(401, "TOKEN_INVALID"),
    /**
     * 系统错误
     */
    SYS_ERR(500, "SYS_ERR")
    ;

    /**
     * 响应状态码
     */
    final private Integer code;
    /**
     * 响应信息
     */
    final private String msg;

    RespEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取状态码
     * @return 状态码
     */
    public int code() {
        return code;
    }

    /**
     * 获取状态信息
     * @return 状态信息
     */
    public String msg() {
        return msg;
    }
}
