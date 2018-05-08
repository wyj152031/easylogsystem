package com.yung.auto.framework.data;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public class ThreadPoolManager {
    private static final int THREAD_POOL_SIZE = 30;
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    /**
     * 执行Task，并返回Future
     *
     * @param task
     * @param <V>
     * @return
     */
    public static <V> Future<V> submit(Callable<V> task) {
        if (task != null) {
            return executor.submit(task);
        }
        return null;
    }

    /**
     * 执行一个Task
     *
     * @param task
     */
    public static void submit(Runnable task) {
        if (task != null) {
            executor.submit(task);
        }
    }

    public static boolean awaitTermination(int timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }

    public static boolean isTerminated() {
        return executor.isTerminated();
    }

    public static void shutdown() {
        executor.shutdown();
    }

    public static boolean isShutdown() {
        return executor.isShutdown();
    }

    public static <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return executor.invokeAll(tasks);
    }

}
