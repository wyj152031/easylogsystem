package com.yung.auto.framework.metric.aggregate;

import com.yung.auto.framework.foundation.AppPropertiesProvider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricDispatchQueue<T> {
    private static MetricDispatchQueue<?> INSTANCE = new MetricDispatchQueue<>();

    private BlockingQueue<T> queue;

    public static MetricDispatchQueue getInstance() {
        return INSTANCE;
    }

    private MetricDispatchQueue() {
        int queueSize = AppPropertiesProvider.getDispatchQueueSize();
        queue = new ArrayBlockingQueue<T>(queueSize);
    }

    public void enqueue(T t) throws InterruptedException {
        this.queue.offer(t);
    }

    public T dequeue() {
        return this.queue.poll();
    }

    public int size() {
        return this.queue.size();
    }
}
