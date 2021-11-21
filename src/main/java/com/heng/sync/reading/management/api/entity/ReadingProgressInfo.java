package com.heng.sync.reading.management.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 阅读进度信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "reading_progress_info")
public class ReadingProgressInfo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 关联书目ID, book_id -> book_resource_info.id
     */
    @TableField(value = "book_id")
    private String bookId;

    /**
     * 当前阅读文本定位符
     */
    @TableField(value = "cur_read_location")
    private String curReadLocation;

    /**
     * 当前阅读进度百分比
     */
    @TableField(value = "cur_read_percentage")
    private BigDecimal curReadPercentage;

    /**
     * 当前阅读章节名称
     */
    @TableField(value = "cur_chapter_name")
    private String curChapterName;

    /**
     * 最近阅读时间
     */
    @TableField(value = "latest_read_time")
    private Date latestReadTime;

    /**
     * 删除标识, 0-正常; 1-已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_BOOK_ID = "book_id";

    public static final String COL_CUR_READ_LOCATION = "cur_read_location";

    public static final String COL_CUR_READ_PERCENTAGE = "cur_read_percentage";

    public static final String COL_CUR_CHAPTER_NAME = "cur_chapter_name";

    public static final String COL_LATEST_READ_TIME = "latest_read_time";

    public static final String COL_IS_DELETED = "is_deleted";
}