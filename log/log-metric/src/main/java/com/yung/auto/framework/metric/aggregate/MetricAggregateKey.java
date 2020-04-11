package com.yung.auto.framework.metric.aggregate;

import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricAggregateKey {
    private String metricName;
    private String primaryKey;
    private MetricEnum metricEnum;
    private Map<String, String> tags;
    private double value;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public MetricEnum getMetricEnum() {
        return metricEnum;
    }

    public void setMetricEnum(MetricEnum metricEnum) {
        this.metricEnum = metricEnum;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
