package com.heng.sync.reading.management.api.commons.enums;

/**
 * 用户锁定状态枚举
 */
public enum AccountLockedEnum {

    /**
     * 解锁、正常
     */
    UN_LOCKED(0),
    /**
     * 锁定
     */
    LOCKED(1)
    ;

    final private Integer locked;

    AccountLockedEnum(Integer locked) {
        this.locked = locked;
    }


    public int locked() {
        return this.locked;
    }

    public static AccountLockedEnum valueOf(Integer locked) {
        if (locked == null) {
            return null;
        }

        switch (locked) {
            case 0:
                return UN_LOCKED;
            case 1:
                return LOCKED;
            default: return null;
        }
    }
}
