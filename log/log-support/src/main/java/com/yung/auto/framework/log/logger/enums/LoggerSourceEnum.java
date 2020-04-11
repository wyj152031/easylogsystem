package com.yung.auto.framework.log.logger.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public enum LoggerSourceEnum {
    APP_LOG(10001);

    private int value;

    LoggerSourceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LoggerSourceEnum findByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if ("10001".equalsIgnoreCase(value)) {
            return APP_LOG;
        }
        return null;
    }
}
