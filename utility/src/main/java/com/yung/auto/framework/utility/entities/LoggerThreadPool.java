package com.yung.auto.framework.utility.entities;

import com.yung.auto.framework.utility.clog.CLog;
import com.yung.auto.framework.utility.collection.CollectionUtils;
import com.yung.auto.framework.utility.common.PropertyManager;
import com.yung.auto.framework.utility.entities.LogEntry;
import com.yung.auto.framework.utility.formate.ILoggerFormat;
import com.yung.auto.framework.utility.tags.LoggerStatics;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class LoggerThreadPool {

    private static volatile BlockingQueue<LogEntry> loggerBlockingQueue = new ArrayBlockingQueue<LogEntry>(600);
    private static final AtomicBoolean running = new AtomicBoolean(false);
    private static final Object locked = new Object();
    private static final String logReject = "logReject";
    private static final ExecutorService exector = Executors.newSingleThreadExecutor();

    public static void put(LogEntry logger) {
        if(logger == null) {
            return;
        }

        if(!PropertyManager.getSwitch()) {
            return;
        }
        if(loggerBlockingQueue.size() > PropertyManager.getLogMaxQueueCount()) {
            CLog.info(logReject,logReject);
            return;
        }
        if(logger.getException() != null && LoggerStatics.getAppEnv().isDEV()) {
            logger.getException().printStackTrace();
        }
        try{
            Map<String,String> contents = logger.getLogContent();
            if(contents == null) {
                contents = new ConcurrentHashMap<>();
            }
            contents.put("LogQueueCount",String.valueOf(loggerBlockingQueue.size()));
            contents.put("LogTime", LoggerStatics.DATE_FORMAT_THREAD_LOCAL.get().format(new Date()));
            loggerBlockingQueue.put(logger);
        }catch (InterruptedException e){
            CLog.error("Logger_Pool_Put",e);
        } finally {
            run();
        }
    }

    private static void run() {
        if(running.get() ||  loggerBlockingQueue.size() <= 0) {
            return;
        }
        running.set(true);
        exector.execute(()->{
            try {
                realWrite();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void realWrite() {
        LogEntry logger;
        while((logger = loggerBlockingQueue.poll()) != null) {
            try {
            List<ILoggerFormat> loggerFormatList = logger.getLoggerFormats();
            if(CollectionUtils.hasElement(loggerFormatList)) {
                for(ILoggerFormat format :loggerFormatList) {
                    format.format(logger);
                }
            }
            logger.realWrite();
            } catch (Exception ex) {
                CLog.error("Logger_Pool_Real_Write", ex);
            }
        }
    }

}
