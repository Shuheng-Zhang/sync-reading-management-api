package com.heng.sync.reading.management.api.commons.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.hutool.core.util.StrUtil;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.result.DataResult;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理器
 */
@RestControllerAdvice
public class ExceptionHandler {

    /**
     * 处理业务异常
     * @param exception 业务异常
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public DataResult businessExceptionHandler(BusinessException exception)  {
        return DataResult.error(exception.getRespEnum(), exception.getErrInfo());
    }

    /**
     * 处理 SaToken 角色异常
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NotRoleException.class)
    public DataResult notRoleExceptionHandler(NotRoleException exception) {
        return DataResult.error(RespEnum.PERMISSION_DENIED, exception.getMessage());
    }

    /**
     * 处理 SaToken 验证异常
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NotLoginException.class)
    public DataResult noLoginExceptionHandler(NotLoginException exception) {
        return DataResult.error(RespEnum.TOKEN_INVALID, exception.getMessage());
    }

    /**
     * 处理系统其他异常
     * @param e 系统其他异常
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public DataResult exceptionHandler(Exception e) {
        return DataResult.error(RespEnum.SYS_ERR,
                StrUtil.builder(e.getClass().getName(),
                        ": ",
                        e.getMessage()).toString()
        );
    }
}
