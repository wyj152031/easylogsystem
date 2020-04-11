package com.yung.auto.framework.log.executor;

import com.yung.auto.framework.common.NamedThreadFactory;
import com.yung.auto.framework.metric.aggregate.MetricAggregateKey;
import com.yung.auto.framework.metric.aggregate.MetricAggregateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricThreadPool {
    private static final AtomicBoolean RUN_STATE = new AtomicBoolean(false);

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20), new NamedThreadFactory("tech-metric-poller"),
            new ThreadPoolExecutor.AbortPolicy());

    private static volatile BlockingQueue<MetricAggregateKey> BLOCKING_QUEUE = new ArrayBlockingQueue<MetricAggregateKey>(6000);

    private final static Logger LOGGER = LoggerFactory.getLogger(MetricThreadPool.class);

    public static void put(MetricAggregateKey key) {
        if (key == null) {
            return;
        }
        try {
            BLOCKING_QUEUE.offer(key, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        } finally {
            start();
        }
    }

    private static void start() {
        if (RUN_STATE.get() || BLOCKING_QUEUE.size() <= 0) {
            return;
        }
        RUN_STATE.set(true);
        EXECUTOR.execute(() -> {
            try {
                realWrite();
            } catch (Exception e) {
                LOGGER.error(e.toString());
            } finally {
                RUN_STATE.set(false);
            }
        });
    }

    private static void realWrite() {
        MetricAggregateKey metricKey;
        while ((metricKey = BLOCKING_QUEUE.poll()) != null) {
            try {
                MetricAggregateManager.putData(metricKey, metricKey.getValue());
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
    }
}
