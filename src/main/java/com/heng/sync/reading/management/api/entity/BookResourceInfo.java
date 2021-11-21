package com.heng.sync.reading.management.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 书目资源信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "book_resource_info")
public class BookResourceInfo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户(拥有者)ID, account_id -> account_info.id
     */
    @TableField(value = "account_id")
    private String accountId;

    /**
     * 书目源文件名
     */
    @TableField(value = "book_origin_file_name")
    private String bookOriginFileName;

    /**
     * 书目资源目录路径
     */
    @TableField(value = "book_resource_url")
    private String bookResourceUrl;

    /**
     * 书目封面路径
     */
    @TableField(value = "book_cover_url")
    private String bookCoverUrl;

    /**
     * 书目资源容量
     */
    @TableField(value = "book_resource_size")
    private Integer bookResourceSize;

    /**
     * 书目导入时间
     */
    @TableField(value = "book_pushed_time")
    private Date bookPushedTime;

    /**
     * 删除标识, 0-正常; 1-已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_ACCOUNT_ID = "account_id";

    public static final String COL_BOOK_ORIGIN_FILE_NAME = "book_origin_file_name";

    public static final String COL_BOOK_RESOURCE_URL = "book_resource_url";

    public static final String COL_BOOK_COVER_URL = "book_cover_url";

    public static final String COL_BOOK_RESOURCE_SIZE = "book_resource_size";

    public static final String COL_BOOK_PUSHED_TIME = "book_pushed_time";

    public static final String COL_IS_DELETED = "is_deleted";
}