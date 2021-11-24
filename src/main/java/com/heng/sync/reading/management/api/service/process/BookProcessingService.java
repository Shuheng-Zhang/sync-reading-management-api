package com.heng.sync.reading.management.api.service.process;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.heng.sync.reading.management.api.dto.book.BookProcessingDto;
import com.heng.sync.reading.management.api.entity.BookMetaInfo;
import com.heng.sync.reading.management.api.entity.BookResourceInfo;
import com.heng.sync.reading.management.api.mapper.BookMetaInfoMapper;
import com.heng.sync.reading.management.api.mapper.BookResourceInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookProcessingService {

    final private static Log LOGGER = LogFactory.get();

    @Value("${appConfig.dataStorage.dataDirRoot}")
    private String dataDirRoot;

    @Value("${appConfig.dataStorage.uploadedTmpPrefix}")
    private String uploadedTmpPrefix;

    @Value("${appConfig.dataStorage.bookResPrefix}")
    private String bookResourceDirRoot;

    @Value("${appConfig.dataStorage.bookCoverPrefix}")
    private String coverPrefixPath;

    @Resource
    private BookResourceInfoMapper bookResourceInfoMapper;

    @Resource
    private BookMetaInfoMapper bookMetaInfoMapper;

    // todo: 处理资源解析
    public void bookResourceAnalysis(List<BookProcessingDto> bookList) {
    }

    // todo: 解压缩
    private String processBookUnpacking(String accountId, String targetBookFilePath) {
        return null;
    }

    // todo: 元数据解析
    private BookMetaInfo processBookMetaInfoAnalysis(String targetBookFilePath) {
        return null;
    }

    // todo: 资源目录解析
    private BookResourceInfo processBookResourceAnalysis(String accountId, String targetBookOriginFileName, String targetBookUnpackedDirPath) {
        return null;
    }
}
