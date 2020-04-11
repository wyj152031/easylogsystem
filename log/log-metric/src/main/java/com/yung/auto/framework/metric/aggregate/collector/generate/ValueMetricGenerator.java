package com.yung.auto.framework.metric.aggregate.collector.generate;

import com.yung.auto.framework.metric.aggregate.unit.MetricCountable;
import com.yung.auto.framework.metric.model.MetricBuilder;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class ValueMetricGenerator implements MetricGenerator {

    @Override
    public void generateMetrics(MetricBuilder builder, MetricCountable countable) {
        builder.addMetric(countable.getMetricName()).setDataPoint(countable.getCreateTime(), countable.getValue()).addTags(countable.getTags());
    }
}
