package com.heng.sync.reading.management.api.commons.enums;

/**
 * 用户类型枚举
 */
public enum AccountTypeEnum {

    /**
     * 阅读者
     */
    READER(0),
    /**
     * 管理员
     */
    ADMIN(1)
    ;

    final private Integer type;

    AccountTypeEnum(Integer type) {
        this.type = type;
    }

    public int type() {
        return type;
    }

    public static AccountTypeEnum valueOf(Integer type) {
        switch (type) {
            case 0:
                return READER;
            case 1:
                return ADMIN;
            default: return null;
        }
    }
}
