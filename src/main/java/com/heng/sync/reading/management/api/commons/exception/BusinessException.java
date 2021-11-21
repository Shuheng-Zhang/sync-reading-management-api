package com.heng.sync.reading.management.api.commons.exception;

import com.heng.sync.reading.management.api.commons.enums.RespEnum;

/**
 * 应用运行时业务异常
 */
public class BusinessException extends RuntimeException {
    private RespEnum respEnum;
    private Object errInfo;

    public BusinessException(RespEnum respEnum) {
        this.respEnum = respEnum;
        this.errInfo = null;
    }

    public BusinessException(RespEnum respEnum, Object errInfo) {
        this.respEnum = respEnum;
        this.errInfo = errInfo;
    }

    private Integer getCode() {
        return respEnum.code();
    }

    public String getMsg() {
        return respEnum.msg();
    }

    public RespEnum getRespEnum() {
        return respEnum;
    }

    public void setRespEnum(RespEnum respEnum) {
        this.respEnum = respEnum;
    }

    public Object getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errMsg) {
        this.errInfo = errInfo;
    }
}
