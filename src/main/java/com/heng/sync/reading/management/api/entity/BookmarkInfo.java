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
    * 书签信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bookmark_info")
public class BookmarkInfo {
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
     * 记录章节名称
     */
    @TableField(value = "marked_chapter_name")
    private String markedChapterName;

    /**
     * 记录文本位置定位符
     */
    @TableField(value = "marked_location")
    private String markedLocation;

    /**
     * 书签创建时间
     */
    @TableField(value = "marked_time")
    private Date markedTime;

    /**
     * 删除标识, 0-正常; 1-已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_BOOK_ID = "book_id";

    public static final String COL_MARKED_CHAPTER_NAME = "marked_chapter_name";

    public static final String COL_MARKED_LOCATION = "marked_location";

    public static final String COL_MARKED_TIME = "marked_time";

    public static final String COL_IS_DELETED = "is_deleted";
}