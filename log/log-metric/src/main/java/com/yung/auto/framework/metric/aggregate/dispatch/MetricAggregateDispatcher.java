package com.yung.auto.framework.metric.aggregate.dispatch;

import com.yung.auto.framework.common.NamedThreadFactory;
import com.yung.auto.framework.common.utils.CollectionUtils;
import com.yung.auto.framework.foundation.AppPropertiesProvider;
import com.yung.auto.framework.metric.aggregate.MetricAggregateManager;
import com.yung.auto.framework.metric.aggregate.MetricDispatchQueue;
import com.yung.auto.framework.metric.aggregate.collector.Collector;
import com.yung.auto.framework.metric.aggregate.collector.CollectorEnum;
import com.yung.auto.framework.metric.aggregate.collector.CollectorFactory;
import com.yung.auto.framework.metric.aggregate.group.MetricAggregateGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricAggregateDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricAggregateDispatcher.class);

    private static MetricAggregateDispatcher INSTANCE;

    private ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("tech-metric-logger"), new ThreadPoolExecutor.AbortPolicy());
    private static MetricDispatchQueue<MetricAggregateGroup> queue = MetricDispatchQueue.getInstance();
    private Collector collector = CollectorFactory.create(CollectorEnum.OPEN_TS_DB);

    public static synchronized MetricAggregateDispatcher getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new MetricAggregateDispatcher();
        }
        return INSTANCE;
    }

    private MetricAggregateDispatcher() {
        final int period = AppPropertiesProvider.getAggregatePeriod();
        this.executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    MetricAggregateManager.flush();
                    MetricAggregateDispatcher.this.dispatch();
                } catch (Exception var2) {
                    LOGGER.error("Data Dispatch (Period {} s) Failed。。。", period, var2);
                }
            }
        }, 0L, (long) period, TimeUnit.MILLISECONDS);
    }

    public void run() {
    }

    private void dispatch() throws Exception {
        boolean flag = true;
        while (flag) {
            if (queue.size() > 0L) {
                MetricAggregateGroup aggGroup = (MetricAggregateGroup) queue.dequeue();
                if (null == aggGroup || !CollectionUtils.hasElement(aggGroup.getMetricMap())) {
                    continue;
                }

                try {
                    collector.send(aggGroup);
                } catch (Exception var7) {
                    LOGGER.error("[Fx] Send Metrics Failed... CycleTime: {}", aggGroup.getCycleTime(), var7);
                    throw var7;
                }
            } else {
                flag = false;
            }
        }
    }
}
