package com.yung.auto.framework.metric.aggregate.group;

import com.yung.auto.framework.common.utils.CalculateUtils;
import com.yung.auto.framework.metric.aggregate.MetricAggregateKey;
import com.yung.auto.framework.metric.aggregate.unit.MetricCountable;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricAggregateGroup {

    private long cycleTime;
    private Map<String, MetricCountable> metricMap;

    public MetricAggregateGroup(long cycleTime) {
        this.cycleTime = cycleTime;
        this.metricMap = new HashMap<>();
    }

    public long getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(long cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Map<String, MetricCountable> getMetricMap() {
        return metricMap;
    }

    public void setMetricMap(Map<String, MetricCountable> metricMap) {
        this.metricMap = metricMap;
    }

    public void putData(MetricAggregateKey key, double value) {
        if (key == null || StringUtils.isEmpty(key.getPrimaryKey())) {
            return;
        }
        MetricCountable metricCountable;
        if (!metricMap.containsKey(key.getPrimaryKey())) {
            metricCountable = new MetricCountable(key.getMetricName());
            metricCountable.setMetricEnum(key.getMetricEnum());
            metricCountable.setMetricName(key.getMetricName());
            metricCountable.setCreateTime(this.cycleTime);
            metricCountable.setAvg(value);
            metricCountable.setCount(1L);
            metricCountable.setMax(value);
            metricCountable.setMin(value);
            metricCountable.setSum(value);
            metricCountable.setValue(value);
            metricCountable.setTags(key.getTags());
            metricMap.put(key.getPrimaryKey(), metricCountable);
        } else {
            metricCountable = metricMap.get(key.getPrimaryKey());
            if (value > metricCountable.getMax()) {
                metricCountable.setMax(value);
            }
            if (value < metricCountable.getMin()) {
                metricCountable.setMin(value);
            }
            metricCountable.setValue(CalculateUtils.add(metricCountable.getValue(), value));
            metricCountable.setSum(CalculateUtils.add(metricCountable.getSum(), value));
            metricCountable.setCount(CalculateUtils.add(metricCountable.getCount(), 1l));
            metricCountable.setAvg(CalculateUtils.div(metricCountable.getSum(), metricCountable.getCount()));
        }
    }
}
