package com.yung.auto.framework.metric.aggregate;

import com.yung.auto.framework.foundation.AppPropertiesProvider;

import java.util.Date;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class CycleTimeInfo {
    private long index;
    /**
     * 循环时间
     */
    private static long cyclePeriod = AppPropertiesProvider.getMetricCyclePeriod();

    public static long getCurrentIndex() {
        return new Date().getTime() / cyclePeriod;
    }

    public static long getCycleTime(long index) {
        return new Date(index * cyclePeriod).getTime();
    }

    public CycleTimeInfo() {
    }

    public long getIndex() {
        return this.index;
    }

    public void setIndex(long index) {
        this.index = index;
    }
}
