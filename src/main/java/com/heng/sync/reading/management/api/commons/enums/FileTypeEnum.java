package com.heng.sync.reading.management.api.commons.enums;

import cn.hutool.core.util.StrUtil;

/**
 * 文件类型枚举
 */
public enum FileTypeEnum {

    /**
     * 电子书出版物 ePub
     */
    EPUB("application/epub", ".epub"),
    /**
     * 电子书出版物 ePub
     */
    EPUB_ZIP("application/epub+zip", ".epub")
    ;

    final private String extName;
    final private String contentType;

    FileTypeEnum(final String contentType, final String extName) {
        this.contentType = contentType;
        this.extName = extName;
    }

    public String contentType() {
        return contentType;
    }

    public String extName() {
        return extName;
    }

    public static FileTypeEnum valueFrom(String contentType) {
        if (StrUtil.isEmptyIfStr(contentType)) {
            return null;
        }
        switch (contentType) {
            case "application/epub":
                return EPUB;
            case "application/epub+zip":
                return EPUB_ZIP;
            default: return null;
        }
    }
}
