package com.heng.sync.reading.management.api.dto.bookmark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * 书签创建数据对象
 */
@Data
@AllArgsConstructor
public class BookmarkCreationDto {

    /**
     * 目标书目ID
     */
    @NonNull
    private String bookId;
    /**
     * 记录章节名称
     */
    @NonNull
    private String markedChapterName;
    /**
     * 记录文本位置定位符
     */
    @NonNull
    private String markedLocation;
}
