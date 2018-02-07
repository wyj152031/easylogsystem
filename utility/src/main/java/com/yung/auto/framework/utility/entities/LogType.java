package com.yung.auto.framework.utility.entities;

/**
 * Created by wangyujing on 2018/1/26.
 */
public enum LogType {
    OTHER(0),
    APP(1),
    URL(2),
    WEB_SERVICE(3),
    SQL(4),
    MEMCACHED(5);

    private final int value;

    private LogType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LogType findByValue(int value) {
        switch (value) {
            case 0:
                return OTHER;
            case 1:
                return APP;
            case 2:
                return URL;
            case 3:
                return WEB_SERVICE;
            case 4:
                return SQL;
            case 5:
                return MEMCACHED;
            default:
                return null;
        }
    }
}
