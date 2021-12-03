package com.heng.sync.reading.management.api.service.process;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.heng.sync.reading.management.api.commons.enums.FileTypeEnum;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileProcessingService {

    /**
     * 将上传文件存储到应用系统
     * @param file 目标上传文件
     * @param storedDirPath 文件存储目录路径
     * @param allowedTypes 可存储文件类型
     * @return 存储后文件名
     */
    public String transferTo(MultipartFile file, String storedDirPath, FileTypeEnum... allowedTypes) {
        if (StrUtil.isEmptyIfStr(storedDirPath) || allowedTypes == null || allowedTypes.length == 0) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        if (file == null || file.isEmpty()) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        try {

            // 检查文件类型
            FileTypeEnum targetFileType = FileTypeEnum.valueFrom(file.getContentType());
            boolean isTypeMatch = false;
            for (FileTypeEnum type : allowedTypes) {
                if (type.equals(targetFileType)) {
                    isTypeMatch = true;
                    break;
                }
            }
            if (!isTypeMatch) {
                throw new BusinessException(RespEnum.FILE_TYPE_UNSUPPORTED);
            }

            // 存储文件
            String fileName = IdUtil.simpleUUID() + targetFileType.extName();
            File destFile = new File(storedDirPath + "/" + fileName);
            file.transferTo(destFile);

            return fileName;
        } catch (Exception e) {
            throw new BusinessException(RespEnum.SYS_ERR, e);
        }
    }

    /**
     * 清理目标文件/文件夹
     * @param targetPath 目标文件/文件夹路径
     */
    public void cleanupFileOrDir(String targetPath) {
        if (StrUtil.isEmptyIfStr(targetPath)) {
            return;
        }

        File targetFile = new File(targetPath);
        FileUtil.del(targetFile);
    }
}
