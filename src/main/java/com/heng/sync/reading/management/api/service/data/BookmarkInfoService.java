package com.heng.sync.reading.management.api.service.data;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.dto.bookmark.BookmarkCreationDto;
import com.heng.sync.reading.management.api.dto.bookmark.BookmarkQueryDto;
import com.heng.sync.reading.management.api.entity.BookmarkInfo;
import com.heng.sync.reading.management.api.mapper.BookResourceInfoMapper;
import com.heng.sync.reading.management.api.mapper.BookmarkInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookmarkInfoService extends ServiceImpl<BookmarkInfoMapper, BookmarkInfo> {

    @Resource
    private BookResourceInfoMapper bookResourceInfoMapper;

    /**
     * 创建书签记录
     * @param creationDto 书签创建数据对象
     */
    public void createBookmark(BookmarkCreationDto creationDto) {
        if (creationDto == null) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        Integer isExisted = bookResourceInfoMapper.queryExistedByBookId(creationDto.getBookId());
        if (isExisted == null) {
            throw new BusinessException(RespEnum.RESOURCE_NOT_FOUND);
        }

        this.baseMapper.insert(new BookmarkInfo(null,
                creationDto.getBookId(),
                creationDto.getMarkedChapterName(),
                creationDto.getMarkedChapterName(),
                new Date()));
    }

    /**
     * 查询目标书目的书签列表
     *
     * @param queryDto 查询数据对象
     * @return
     */
    public IPage<BookmarkInfo> findByBookId(BookmarkQueryDto queryDto) {

        if (queryDto == null) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return this.baseMapper.queryByBookId(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), queryDto);
    }

    /**
     * 批量删除指定书签
     *
     * @param bookmarkIds 目标书签ID列表
     */
    public void purgeByBookmarkIds(List<String> bookmarkIds) {
        if (bookmarkIds == null || bookmarkIds.isEmpty()) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }
        this.baseMapper.deleteBatchIds(bookmarkIds);
    }
}
