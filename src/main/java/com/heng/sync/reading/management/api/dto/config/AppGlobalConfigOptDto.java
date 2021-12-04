package com.heng.sync.reading.management.api.dto.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应用全局配置操作数据对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppGlobalConfigOptDto {

    /**
     * 用户账号ID
     */
    private String accountId;
    /**
     * 应用界面主题
     */
    private Integer appTheme;
    /**
     * 应用信息同步模式
     */
    private Integer appInfoSyncMode;
    /**
     * 阅读器主题
     */
    private Integer readerTheme;
    /**
     * 阅读器字号
     */
    private Integer readerFontSize;
    /**
     * 阅读器字体
     */
    private String readerFontFamily;
    /**
     * 阅读器页面文本行间距
     */
    private Integer readerTextSpace;

}
