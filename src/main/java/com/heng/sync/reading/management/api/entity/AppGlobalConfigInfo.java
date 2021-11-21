package com.heng.sync.reading.management.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 应用全局配置信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_global_config_info")
public class AppGlobalConfigInfo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户账号ID, account_id -> account_info.id
     */
    @TableField(value = "account_id")
    private String accountId;

    /**
     * 应用界面主题
     */
    @TableField(value = "app_theme")
    private Integer appTheme;

    /**
     * 应用信息同步模式
     */
    @TableField(value = "app_info_sync_mode")
    private Integer appInfoSyncMode;

    /**
     * 阅读器主题
     */
    @TableField(value = "reader_theme")
    private Integer readerTheme;

    /**
     * 阅读器字号
     */
    @TableField(value = "reader_font_size")
    private Integer readerFontSize;

    /**
     * 阅读器字体
     */
    @TableField(value = "reader_font_family")
    private String readerFontFamily;

    /**
     * 阅读器页面文本行间距
     */
    @TableField(value = "reader_text_space")
    private Integer readerTextSpace;

    /**
     * 删除标识, 0-正常; 1-已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_ACCOUNT_ID = "account_id";

    public static final String COL_APP_THEME = "app_theme";

    public static final String COL_APP_INFO_SYNC_MODE = "app_info_sync_mode";

    public static final String COL_READER_THEME = "reader_theme";

    public static final String COL_READER_FONT_SIZE = "reader_font_size";

    public static final String COL_READER_FONT_FAMILY = "reader_font_family";

    public static final String COL_READER_TEXT_SPACE = "reader_text_space";

    public static final String COL_IS_DELETED = "is_deleted";
}