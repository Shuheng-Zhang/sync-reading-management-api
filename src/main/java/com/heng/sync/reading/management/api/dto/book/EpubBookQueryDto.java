package com.heng.sync.reading.management.api.dto.book;

import com.heng.sync.reading.management.api.commons.request.PageQuery;
import lombok.*;

/**
 * ePub 电子书查询数据对象
 * 启用分页查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class EpubBookQueryDto extends PageQuery {

    /**
     * 用户账号ID
     */
    @NonNull
    private String accountId;

    /**
     * 书目标题
     */
    private String bookTitle;
    /**
     * 作者
     */
    private String authors;

    /**
     * 删除标识, 0-正常; 1-已删除
     */
    private Integer isDeleted;

    public EpubBookQueryDto() {
        this.isDeleted = 0;
    }
}
