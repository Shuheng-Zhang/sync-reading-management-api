package com.heng.sync.reading.management.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 书目元数据
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "book_meta_info")
public class BookMetaInfo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 书目ID, book_id -> book_resource_info.id
     */
    @TableField(value = "book_id")
    private String bookId;

    /**
     * 书目标题
     */
    @TableField(value = "book_title")
    private String bookTitle;

    /**
     * 书目作者列表, 使用逗号分隔
     */
    @TableField(value = "book_authors")
    private String bookAuthors;

    /**
     * 书目简介
     */
    @TableField(value = "book_description")
    private String bookDescription;

    /**
     * 删除标识, 0-正常; 1-已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_BOOK_ID = "book_id";

    public static final String COL_BOOK_TITLE = "book_title";

    public static final String COL_BOOK_AUTHORS = "book_authors";

    public static final String COL_BOOK_DESCRIPTION = "book_description";

    public static final String COL_IS_DELETED = "is_deleted";
}