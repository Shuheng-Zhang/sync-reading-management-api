package com.heng.sync.reading.management.api.service.data;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.dto.book.EpubBookQueryDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultAnalysisDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultBaseDto;
import com.heng.sync.reading.management.api.dto.book.QueryResultDetailDto;
import com.heng.sync.reading.management.api.mapper.BookQueryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EpubBookService {

    @Resource
    private BookQueryMapper bookQueryMapper;

    /**
     * 查询书目基本信息
     * @param queryDto 查询对象
     * @return
     */
    public IPage<QueryResultBaseDto> findBooksWithBaseInfo(EpubBookQueryDto queryDto) {

        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithBaseInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), accountId, queryDto.getBookTitle(), queryDto.getAuthors()
        );
    }

    /**
     * 查询书目详细信息
     * @param queryDto 查询对象
     * @return
     */
    public IPage<QueryResultDetailDto> findBooksWithDetailInfo(EpubBookQueryDto queryDto) {
        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithDetailInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), accountId, queryDto.getBookTitle(), queryDto.getAuthors());
    }

    /**
     * 查询书目统计信息
     * @param queryDto 查询对象
     * @return
     */
    public IPage<QueryResultAnalysisDto> findBooksWithAnalysisInfo(EpubBookQueryDto queryDto) {
        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithAnalysisInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), accountId, queryDto.getBookTitle(), queryDto.getAuthors());
    }
}
