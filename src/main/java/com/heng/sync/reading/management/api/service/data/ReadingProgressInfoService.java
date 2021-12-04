package com.heng.sync.reading.management.api.service.data;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.dto.reading.ReadingProgressInfoOptDto;
import com.heng.sync.reading.management.api.dto.reading.ReadingProgressQueryDto;
import com.heng.sync.reading.management.api.entity.ReadingProgressInfo;
import com.heng.sync.reading.management.api.mapper.BookResourceInfoMapper;
import com.heng.sync.reading.management.api.mapper.ReadingProgressInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ReadingProgressInfoService extends ServiceImpl<ReadingProgressInfoMapper, ReadingProgressInfo> {

    @Resource
    private BookResourceInfoMapper bookResourceInfoMapper;

    /**
     * 查询指定书目阅读进度信息
     * @param queryDto 查询数据对象
     * @return
     */
    public ReadingProgressInfo findByBookId(ReadingProgressQueryDto queryDto) {
        if (queryDto == null || StrUtil.isEmptyIfStr(queryDto.getBookId())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return this.baseMapper.queryByBookId(queryDto.getBookId());
    }

    /**
     * 创建/更新阅读进度信息
     * @param optDto 进度信息操作数据对象
     */
    public void createOrUpdateReadingProgress(ReadingProgressInfoOptDto optDto) {
        if (optDto == null || StrUtil.isEmptyIfStr(optDto.getBookId())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        Integer isResourceExisted = bookResourceInfoMapper.queryExistedByBookId(optDto.getBookId());
        if (isResourceExisted == null) {
            throw new BusinessException(RespEnum.RESOURCE_NOT_FOUND);
        }

        ReadingProgressInfo progressInfo = this.baseMapper.queryByBookId(optDto.getBookId());
        if (progressInfo == null) {
            this.baseMapper.insert(new ReadingProgressInfo(null, optDto.getBookId(), optDto.getCurReadLocation(), optDto.getCurReadPercentage(), optDto.getCurChapterName(), new Date()));
        } else {
            progressInfo.setCurChapterName(optDto.getCurChapterName());
            progressInfo.setCurReadLocation(optDto.getCurReadLocation());
            progressInfo.setCurReadPercentage(optDto.getCurReadPercentage());
            progressInfo.setLatestReadTime(new Date());
            this.baseMapper.updateById(progressInfo);
        }
    }

    /**
     * 删除指定书目阅读进度信息
     * @param bookId 目标书目ID
     */
    public void deleteByBookId(String bookId) {
        if (StrUtil.isEmptyIfStr(bookId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        this.baseMapper.deleteByBookId(bookId);
    }
}
