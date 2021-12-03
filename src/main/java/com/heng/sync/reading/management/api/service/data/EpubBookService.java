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

    public IPage<QueryResultBaseDto> findBooksWithBaseInfo(EpubBookQueryDto queryDto) {

        String accountId = queryDto.getAccountId();
        if (StrUtil.isBlankIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return bookQueryMapper.queryBooksWithBaseInfo(new Page<>(queryDto.getCurPage(), queryDto.getSizePerPage()), accountId, queryDto.getBookTitle(), queryDto.getAuthors()
        );
    }

    public IPage<QueryResultDetailDto> findBooksWithDetailInfo(EpubBookQueryDto queryDto) {
        return null;
    }

    public IPage<QueryResultAnalysisDto> findBooksWithAnalysisInfo(EpubBookQueryDto queryDto) {
        return null;
    }
}
