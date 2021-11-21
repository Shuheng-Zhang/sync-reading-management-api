package com.heng.sync.reading.management.api.commons.result;

import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import lombok.Data;

/**
 * 统一响应数据结构
 */
@Data
public class DataResult {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     * 当响应为 错误/异常 时则为错误详细信息
     */
    private Object data;

    private DataResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private DataResult(RespEnum respEnum, Object data) {
        this.code = respEnum.code();
        this.msg = respEnum.msg();
        this.data = data;
    };

    /**
     * 请求成功
     * @return
     */
    public static DataResult success() {
        return new DataResult(RespEnum.OK, null);
    }

    /**
     * 请求成功 - 返回承载数据
     * @param data 响应数据
     * @return
     */
    public static DataResult success(Object data) {
        return new DataResult(RespEnum.OK, data);
    }

    /**
     * 请求错误
     * @param respEnum 响应枚举
     * @param err 详细错误信息
     * @return
     */
    public static DataResult error(RespEnum respEnum, Object err) {
        return new DataResult(respEnum, err);
    }
}
