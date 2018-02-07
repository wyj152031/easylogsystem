package com.yung.auto.framework.utility.entities;


import com.yung.auto.framework.utility.common.Strings;

/**
 * @autor wangyujing
 * @since 2018/1/26.
 */
public enum LoggerSourceEnum {
    CLOG(10010),DLOG(10011);
    private int key;

    LoggerSourceEnum(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static LoggerSourceEnum value(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        if ("10010".equalsIgnoreCase(key)) {
            return CLOG;
        } else if("10011".equalsIgnoreCase(key)) {
            return DLOG;
        }
        return null;
    }

}
