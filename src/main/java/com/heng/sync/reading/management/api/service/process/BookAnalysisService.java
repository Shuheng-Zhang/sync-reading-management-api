package com.heng.sync.reading.management.api.service.process;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.heng.sync.reading.management.api.dto.book.BookAnalysisDto;
import com.heng.sync.reading.management.api.entity.BookResourceInfo;
import com.heng.sync.reading.management.api.mapper.BookMetaInfoMapper;
import com.heng.sync.reading.management.api.mapper.BookResourceInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Service
public class BookAnalysisService {

    final private static Log LOGGER = LogFactory.get();

    @Value("${appConfig.dataStorage.dataDirRoot}")
    private String dataDirRoot;

    @Value("${appConfig.dataStorage.bookResPrefix}")
    private String bookResourceDirRoot;

    @Resource
    private BookResourceInfoMapper bookResourceInfoMapper;

    @Resource
    private BookMetaInfoMapper bookMetaInfoMapper;

    public void bookResourceAnalysis(List<BookAnalysisDto> bookList) {
        if (bookList == null || bookList.isEmpty()) {
            return;
        }


    }

    public void bookMetaInfoAnalysis(List<BookAnalysisDto> bookList) {

    }

    private void processBookResourceAnalysis(String accountId, List<BookAnalysisDto> bookList) {
        boolean isAllOk = true;
        List<Map<String, BookResourceInfo>> processToDbList = new ArrayList<>();
        for (BookAnalysisDto analysisDto : bookList) {
            if (!FileUtil.exist(analysisDto.getBookOriginFilePath())) {
                isAllOk = false;
                continue;
            }

            String relatedUnpackedPath = "/" + accountId + bookResourceDirRoot + "/" + analysisDto.getBookId();
            String unpackedPath = dataDirRoot + relatedUnpackedPath;
            File unpacked = new File(unpackedPath);
            ZipUtil.unzip(new File(analysisDto.getBookOriginFilePath()), unpacked);
            long resSize = FileUtil.size(unpacked);
            Date pushedTime = new Date(DateUtil.current());

            BookResourceInfo bookResourceInfo = new BookResourceInfo(analysisDto.getBookId(), accountId, analysisDto.getBookOriginFileName(), relatedUnpackedPath, null, (int) resSize, pushedTime, 0);
            Map<String, BookResourceInfo> processItem = new HashMap<>();
            processItem.put(analysisDto.getBookId(), bookResourceInfo);

            processToDbList.add(processItem);
        }
    }
}
