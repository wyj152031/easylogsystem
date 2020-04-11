package com.yung.auto.framework.log.utils;

import com.yung.auto.framework.log.logger.Logger;
import com.yung.auto.framework.log.logger.LoggerFactory;
import com.yung.auto.framework.log.logger.enums.LoggerLevelEnum;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class LoggerUtils {

    public final static ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat;
    });

    public static void info(String msg) {
        write(LoggerLevelEnum.INFO, msg, null);
    }


    public static void info(String msg, Exception ex) {
        write(LoggerLevelEnum.INFO, msg, ex);
    }

    public static void debug(String msg) {
        write(LoggerLevelEnum.DEBUG, msg, null);
    }

    public static void debug(String msg, Exception ex) {
        write(LoggerLevelEnum.DEBUG, msg, ex);
    }

    public static void warn(String msg) {
        write(LoggerLevelEnum.WARN, msg, null);
    }

    public static void warn(String msg, Exception ex) {
        write(LoggerLevelEnum.WARN, msg, ex);
    }

    public static void error(String msg) {
        write(LoggerLevelEnum.ERROR, msg, null);
    }

    public static void error(String msg, Exception ex) {
        write(LoggerLevelEnum.ERROR, msg, ex);
    }

    public static void error(String msg, Throwable t) {
        write(LoggerLevelEnum.ERROR, msg, (Exception) t);
    }

    private static void write(LoggerLevelEnum levelEnum, String msg, Exception ex) {
        Logger logger = LoggerFactory.createLogger(LoggerSourceEnum.APP_LOG);
        logger.addLogTag("threadId", Thread.currentThread().toString());
        logger.addLogTag("threadName", Thread.currentThread().getName());
        logger.addLogContent("msg", msg);
        logger.setLogLevel(levelEnum);
        logger.setLogException(ex);
        logger.write();
    }
}
