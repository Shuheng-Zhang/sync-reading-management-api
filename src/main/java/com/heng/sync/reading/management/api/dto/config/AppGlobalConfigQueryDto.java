package com.heng.sync.reading.management.api.dto.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 应用全局配置查询数据对象
 */
@Data
@AllArgsConstructor
public class AppGlobalConfigQueryDto {
    /**
     * 目标用户账号ID
     */
    private String accountId;
}
