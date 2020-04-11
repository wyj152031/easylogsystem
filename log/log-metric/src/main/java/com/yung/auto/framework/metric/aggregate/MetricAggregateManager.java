package com.yung.auto.framework.metric.aggregate;

import com.google.common.base.Preconditions;
import com.yung.auto.framework.common.utils.CollectionUtils;
import com.yung.auto.framework.metric.aggregate.dispatch.MetricAggregateDispatcher;
import com.yung.auto.framework.metric.aggregate.group.MetricAggregateGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricAggregateManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricAggregateManager.class);

    private static CycleTimeInfo cycleTimeInfo = new CycleTimeInfo();
    private static MetricAggregateGroup buffer = new MetricAggregateGroup(CycleTimeInfo.getCycleTime(CycleTimeInfo.getCurrentIndex()));

    private static MetricDispatchQueue<MetricAggregateGroup> queue = MetricDispatchQueue.getInstance();
    private static MetricAggregateDispatcher dispatcher = MetricAggregateDispatcher.getInstance();

    public static void putData(MetricAggregateKey key, double value) {
        Preconditions.checkNotNull(key);
        flush();
        buffer.putData(key, value);
    }

    public static synchronized void flush() {
        try {
            long currIndex = CycleTimeInfo.getCurrentIndex();
            if (currIndex > cycleTimeInfo.getIndex()) {
                MetricAggregateGroup preBuffer = buffer;
                if (null != preBuffer && CollectionUtils.hasElement(preBuffer.getMetricMap())) {
                    queue.enqueue(preBuffer);
                }

                buffer = new MetricAggregateGroup(CycleTimeInfo.getCycleTime(currIndex));
                cycleTimeInfo.setIndex(currIndex);
            }
        } catch (Exception var4) {
            LOGGER.error("Flush to Metric Dispatch Queue Failed...", var4);
        }
    }
}
