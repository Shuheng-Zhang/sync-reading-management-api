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
     * 书目目录章节统计
     */
    @TableField(value = "book_contents_count")
    private Integer bookContentsCount;

    /**
     * 书目封面URL
     */
    @TableField(value = "book_cover_url")
    private String bookCoverUrl;

    /**
     * 书目OPF文件URL
     */
    @TableField(value = "book_opf_url")
    private String bookOpfUrl;

    public static final String COL_ID = "id";

    public static final String COL_BOOK_ID = "book_id";

    public static final String COL_BOOK_TITLE = "book_title";

    public static final String COL_BOOK_AUTHORS = "book_authors";

    public static final String COL_BOOK_DESCRIPTION = "book_description";

    public static final String COL_BOOK_CONTENTS_COUNT = "book_contents_count";

    public static final String COL_BOOK_COVER_URL = "book_cover_url";

    public static final String COL_BOOK_OPF_URL = "book_opf_url";
}