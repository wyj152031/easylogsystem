package com.yung.auto.framework.log.logger.enums;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public enum LoggerEnum {
    OTHER(0),
    APP(1),
    ONLINE(2),
    RPC_SERVICE(3),
    SQL(4),
    REDIS(5);

    private final int value;

    LoggerEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LoggerEnum findByValue(int value) {
        switch (value) {
            case 1:
                return APP;
            case 2:
                return ONLINE;
            case 3:
                return RPC_SERVICE;
            case 4:
                return SQL;
            case 5:
                return REDIS;
            default:
                return OTHER;
        }
    }
}
