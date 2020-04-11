package com.yung.auto.framework.metric.aggregate.collector.generate;

import com.yung.auto.framework.metric.aggregate.MetricEnum;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricGeneratorFactory {

    public static MetricGenerator create(MetricEnum metricEnum) {
        switch (metricEnum) {
            case VALUE:
                return new ValueMetricGenerator();
            default:
                return new AllMetricGenerator();
        }
    }
}
