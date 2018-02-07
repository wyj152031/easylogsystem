package com.yung.auto.framework.utility.trace;

import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;
import com.yung.auto.framework.utility.file.LogFileTxtManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyujing on 2018/1/26.
 */
public class CommonLogger implements ILog {
    private String logName = "";

    public CommonLogger(String logName) {
        if (Strings.isNullOrEmpty(logName)) {
            this.logName = "defaultLogName";
        } else {
            this.logName = logName;
        }
    }

    private void doWriteLog(LogLevel logLevel, LogType logType, String title, String message, Throwable throwable, Map<String, String> attrs,
                            long createdTime) {
        LogFileTxtManager logFileTxtManager = LogFileTxtManager.getInstance();
        logFileTxtManager.writeLog(logLevel, logType, title, message, throwable, attrs, createdTime);
    }

    public void writeLog(LogLevel logLevel, LogType logType, String title, String message, Throwable throwable, Map<String, String> attrs,
                         long createdTime) {
        doWriteLog(logLevel, logType, title, message, throwable, attrs == null ? new HashMap<String, String>() : new HashMap<String, String>(attrs),
                createdTime);
    }

    public void writeLog(LogLevel logLevel, LogType logType, String title, String message, Throwable throwable, Map<String, String> attrs) {
        writeLog(logLevel, logType, title, message, throwable, attrs, System.currentTimeMillis());
    }

    public void writeLog(LogLevel logLevel, String title, String message, Throwable throwable,Map<String, String> attrs) {
        writeLog(logLevel, LogType.APP, title, message, throwable, attrs, System.currentTimeMillis());
    }

    public void debug(String title, String message) {
        this.writeLog(LogLevel.DEBUG, title, message,null, null);
    }

    public void debug(String title, Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,null);
    }

    public void debug(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,message,null,attrs);
    }

    public void debug(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,attrs);
    }

    public void debug(String message) {
        this.writeLog(LogLevel.DEBUG,null,message,null,null);
    }

    public void debug(Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,null);
    }

    public void debug(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,message,null,attrs);
    }

    public void debug(Throwable throwable, Map<String, String> attrs) {
        return;
    }

    public void error(String title, String message) {
        this.writeLog(LogLevel.DEBUG,title,message,null,null);
    }

    public void error(String title, Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,null);
    }

    public void error(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,message,null,attrs);
    }

    public void error(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,attrs);
    }

    public void error(String message) {
        this.writeLog(LogLevel.DEBUG,null,message,null,null);
    }

    public void error(Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,null);
    }

    public void error(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,message,null,attrs);
    }

    public void error(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,attrs);
    }

    public void fatal(String title, String message) {
        this.writeLog(LogLevel.DEBUG,title,message,null,null);
    }

    public void fatal(String title, Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,null);
    }

    public void fatal(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,message,null,attrs);
    }

    public void fatal(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,attrs);
    }

    public void fatal(String message) {
        this.writeLog(LogLevel.DEBUG,null,message,null,null);
    }

    public void fatal(Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,null);
    }

    public void fatal(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,message,null,attrs);
    }

    public void fatal(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,attrs);
    }

    public void info(String title, String message) {
        this.writeLog(LogLevel.DEBUG,title,null,null,null);
    }

    public void info(String title, Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,null);
    }

    public void info(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,null,attrs);
    }

    public void info(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,attrs);
    }

    public void info(String message) {
        this.writeLog(LogLevel.DEBUG,null,null,null,null);
    }

    public void info(Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,null);
    }

    public void info(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,null,null,attrs);
    }

    public void info(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG, null,null,throwable,attrs);
    }

    public void warn(String title, String message) {
        this.writeLog(LogLevel.DEBUG,title,null,null,null);
    }

    public void warn(String title, Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,null);
    }

    public void warn(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,null,attrs);
    }

    public void warn(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,title,null,throwable,attrs);
    }

    public void warn(String message) {
        this.writeLog(LogLevel.DEBUG,null,message,null,null);
    }

    public void warn(Throwable throwable) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,null);
    }

    public void warn(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,null,null,attrs);
    }

    public void warn(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG,null,null,throwable,attrs);
    }
}
