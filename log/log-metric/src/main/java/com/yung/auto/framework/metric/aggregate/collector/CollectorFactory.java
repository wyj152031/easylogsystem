package com.yung.auto.framework.metric.aggregate.collector;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class CollectorFactory {
    public static Collector create(CollectorEnum collectorEnum) {
        switch (collectorEnum) {
            case OPEN_TS_DB:
                return new OpentsdbCollector();
            default:
                return null;
        }
    }
}
