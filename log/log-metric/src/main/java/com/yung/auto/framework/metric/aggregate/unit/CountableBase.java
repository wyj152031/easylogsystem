package com.yung.auto.framework.metric.aggregate.unit;

import com.yung.auto.framework.metric.utils.CountableKeyUtils;

import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class CountableBase implements Countable {

    /**
     * 埋点名称
     */
    private String metricName;
    /**
     * 埋点创建时间，单位为s
     */
    private long createTime;
    /**
     * 埋点值
     */
    private double value;
    /**
     * 埋点tag
     */
    private Map<String, String> tags;

    public CountableBase(String metricName) {
        this.metricName = metricName;
    }

    public CountableBase(String metricName, long createTime) {
        this.metricName = metricName;
        this.createTime = createTime;
    }

    public CountableBase(String metricName, long createTime, double value) {
        this.metricName = metricName;
        this.createTime = createTime;
        this.value = value;
    }

    public CountableBase(String metricName, long createTime, double value, Map<String, String> tags) {
        this.metricName = metricName;
        this.createTime = createTime;
        this.value = value;
        this.tags = tags;
    }

    @Override
    public String getKey() {
        return CountableKeyUtils.generateKey(this.metricName, this.tags);
    }

    @Override
    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
