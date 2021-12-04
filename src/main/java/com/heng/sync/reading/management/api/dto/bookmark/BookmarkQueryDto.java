package com.heng.sync.reading.management.api.dto.bookmark;

import com.heng.sync.reading.management.api.commons.request.PageQuery;
import lombok.*;

/**
 * 书签查询数据对象
 * 启用分页查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkQueryDto extends PageQuery {

    /**
     * 目标书目ID
     */
    @NonNull
    private String bookId;

}
