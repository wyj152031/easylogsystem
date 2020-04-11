package com.yung.auto.framework.metric.aggregate.unit;

import com.yung.auto.framework.metric.aggregate.MetricEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricCountable extends CountableBase {

    private MetricEnum metricEnum;
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public MetricCountable(String str) {
        super(str);
    }

    public MetricEnum getMetricEnum() {
        return metricEnum;
    }

    public void setMetricEnum(MetricEnum metricEnum) {
        this.metricEnum = metricEnum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void addTag(String key,String value) {
        Map<String, String> tags = this.getTags();
        if(tags == null) {
            tags = new HashMap();
            this.setTags(tags);
        }

        ((Map)tags).put(key, value);
    }
}
