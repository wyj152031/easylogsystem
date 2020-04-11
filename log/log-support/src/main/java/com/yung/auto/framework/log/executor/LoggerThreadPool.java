package com.yung.auto.framework.log.executor;

import com.yung.auto.framework.common.NamedThreadFactory;
import com.yung.auto.framework.log.logger.LoggerConstants;
import com.yung.auto.framework.log.utils.LoggerUtils;
import com.yung.auto.framework.log.logger.entity.LoggerEvent;
import com.yung.auto.framework.log.logger.formate.LoggerFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class LoggerThreadPool {
    private static final AtomicBoolean RUN_STATE = new AtomicBoolean(false);

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20), new NamedThreadFactory("tech-logger"),
            new ThreadPoolExecutor.AbortPolicy());

    private static volatile BlockingQueue<LoggerEvent> BLOCKING_QUEUE = new ArrayBlockingQueue<LoggerEvent>(6000);

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggerThreadPool.class);

    public static void put(LoggerEvent logEvent) {
        if (logEvent == null) {
            return;
        }
        try {
            // TODO 开关功能以及其他补充功能
            Map<String, String> contents = logEvent.getLogContent();
            if (contents == null) {
                contents = new HashMap<>();
            }
            contents.put(LoggerConstants.LOG_QUEUE_COUNT, String.valueOf(BLOCKING_QUEUE.size()));
            contents.put(LoggerConstants.LOG_TIME, LoggerUtils.DATE_FORMAT.get().format(new Date()));
            BLOCKING_QUEUE.offer(logEvent, 10, TimeUnit.SECONDS);
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
        LoggerEvent logEvent;
        while ((logEvent = BLOCKING_QUEUE.poll()) != null) {
            try {
                List<LoggerFormat> formats = logEvent.getFormats();
                if (CollectionUtils.isEmpty(formats)) {
                    continue;
                }
                for (LoggerFormat format : formats) {
                    format.format(logEvent);
                }
                logEvent.realWrite();
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
    }
}
