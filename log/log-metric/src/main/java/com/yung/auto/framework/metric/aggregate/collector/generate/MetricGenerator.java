package com.yung.auto.framework.metric.aggregate.collector.generate;

import com.yung.auto.framework.metric.aggregate.unit.MetricCountable;
import com.yung.auto.framework.metric.model.MetricBuilder;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface MetricGenerator {

    /**
     * 生成埋点数据
     *
     * @param builder
     * @param countable
     */
    void generateMetrics(MetricBuilder builder, MetricCountable countable);
}
