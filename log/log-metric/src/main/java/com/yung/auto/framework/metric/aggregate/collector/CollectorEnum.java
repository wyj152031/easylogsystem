package com.yung.auto.framework.metric.aggregate.collector;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public enum  CollectorEnum {

    /**
     * opentsdb
     */
    OPEN_TS_DB("opentsdb");

    private String value;

    CollectorEnum(String value) {
        this.value = value;
    }

    public static CollectorEnum findByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return OPEN_TS_DB;
        }
        for (CollectorEnum item : CollectorEnum.values()) {
            if (value.equalsIgnoreCase(item.value)) {
                return item;
            }
        }
        return OPEN_TS_DB;
    }

    public String valueOf() {
        return this.value;
    }
}
