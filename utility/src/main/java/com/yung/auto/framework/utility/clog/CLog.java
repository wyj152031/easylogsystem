package com.yung.auto.framework.utility.clog;

import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class CLog {
    public static void info(String title, String message) {
        CLogFactory.getCLoggingTracer().log(LogType.APP, LogLevel.INFO, title, message);
    }

    public static void info(String title) {
        CLogFactory.getCLoggingTracer().log(LogType.APP, LogLevel.INFO, title);
    }

    public static void error(String title, String message) {
        CLogFactory.getCLoggingTracer().log(LogType.APP, LogLevel.ERROR, title, message);
    }

    public static void error(String title, Exception e) {
        CLogFactory.getCLoggingTracer().log(LogType.APP, LogLevel.ERROR, title, e);
    }

    public static void warn(String title,Exception e){
        CLogFactory.getCLoggingTracer().log(LogType.APP, LogLevel.WARN, title, e);
    }

    public static void warn(String title, String message) {
        CLogFactory.getCLoggingTracer().log(LogType.APP, LogLevel.WARN, title, message);
    }
}
