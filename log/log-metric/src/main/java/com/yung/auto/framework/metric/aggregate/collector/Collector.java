package com.yung.auto.framework.metric.aggregate.collector;

import com.yung.auto.framework.metric.aggregate.group.MetricAggregateGroup;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface Collector {

    void send(MetricAggregateGroup group) throws Exception;
}
