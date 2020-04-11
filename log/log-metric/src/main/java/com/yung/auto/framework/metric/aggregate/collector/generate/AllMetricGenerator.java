package com.yung.auto.framework.metric.aggregate.collector.generate;

import com.yung.auto.framework.metric.aggregate.unit.MetricCountable;
import com.yung.auto.framework.metric.model.MetricBuilder;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class AllMetricGenerator implements MetricGenerator {

    @Override
    public void generateMetrics(MetricBuilder builder, MetricCountable countable) {

        builder.addMetric(countable.getMetricName()).setDataPoint(countable.getCreateTime(), countable.getValue()).addTags(countable.getTags());
        builder.addMetric(countable.getMetricName() + ".count").setDataPoint(countable.getCreateTime(), countable.getCount()).addTags(countable.getTags());
        builder.addMetric(countable.getMetricName() + ".sum").setDataPoint(countable.getCreateTime(),
                countable.getSum()).addTags(countable.getTags());
        builder.addMetric(countable.getMetricName() + ".avg").setDataPoint(countable.getCreateTime(),
                countable.getAvg()).addTags(countable.getTags());
        builder.addMetric(countable.getMetricName() + ".max").setDataPoint(countable.getCreateTime(),
                countable.getMax()).addTags(countable.getTags());
        builder.addMetric(countable.getMetricName() + ".min").setDataPoint(countable.getCreateTime(),
                countable.getMin()).addTags(countable.getTags());
    }
}
