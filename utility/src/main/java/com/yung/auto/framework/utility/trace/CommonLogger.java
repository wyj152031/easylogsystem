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

    public void writeLog(LogLevel logLevel, String title, String message, Throwable throwable, Map<String, String> attrs) {
        writeLog(logLevel, LogType.APP, title, message, throwable, attrs, System.currentTimeMillis());
    }

    @Override
    public void debug(String title, String message) {
        this.writeLog(LogLevel.DEBUG, title, message, null, null);
    }

    @Override
    public void debug(String title, Throwable throwable) {
        this.writeLog(LogLevel.DEBUG, title, null, throwable, null);
    }

    @Override
    public void debug(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG, title, message, null, attrs);
    }

    @Override
    public void debug(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG, title, null, throwable, attrs);
    }

    @Override
    public void debug(String message) {
        this.writeLog(LogLevel.DEBUG, null, message, null, null);
    }

    @Override
    public void debug(Throwable throwable) {
        this.writeLog(LogLevel.DEBUG, null, null, throwable, null);
    }

    @Override
    public void debug(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG, null, message, null, attrs);
    }

    @Override
    public void debug(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.DEBUG, null, null, throwable, attrs);
    }

    @Override
    public void error(String title, String message) {
        this.writeLog(LogLevel.ERROR, title, message, null, null);
    }

    @Override
    public void error(String title, Throwable throwable) {
        this.writeLog(LogLevel.ERROR, title, null, throwable, null);
    }

    @Override
    public void error(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.ERROR, title, message, null, attrs);
    }

    @Override
    public void error(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.ERROR, title, null, throwable, attrs);
    }

    @Override
    public void error(String message) {
        this.writeLog(LogLevel.ERROR, null, message, null, null);
    }

    @Override
    public void error(Throwable throwable) {
        this.writeLog(LogLevel.ERROR, null, null, throwable, null);
    }

    @Override
    public void error(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.ERROR, null, message, null, attrs);
    }

    @Override
    public void error(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.ERROR, null, null, throwable, attrs);
    }

    @Override
    public void fatal(String title, String message) {
        this.writeLog(LogLevel.FATAL, title, message, null, null);
    }

    @Override
    public void fatal(String title, Throwable throwable) {
        this.writeLog(LogLevel.FATAL, title, null, throwable, null);
    }

    @Override
    public void fatal(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.FATAL, title, message, null, attrs);
    }

    @Override
    public void fatal(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.FATAL, title, null, throwable, attrs);
    }

    @Override
    public void fatal(String message) {
        this.writeLog(LogLevel.FATAL, null, message, null, null);
    }

    @Override
    public void fatal(Throwable throwable) {
        this.writeLog(LogLevel.FATAL, null, null, throwable, null);
    }

    @Override
    public void fatal(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.FATAL, null, message, null, attrs);
    }

    @Override
    public void fatal(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.FATAL, null, null, throwable, attrs);
    }

    @Override
    public void info(String title, String message) {
        this.writeLog(LogLevel.INFO, title, null, null, null);
    }

    @Override
    public void info(String title, Throwable throwable) {
        this.writeLog(LogLevel.INFO, title, null, throwable, null);
    }

    @Override
    public void info(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.INFO, title, null, null, attrs);
    }

    @Override
    public void info(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.INFO, title, null, throwable, attrs);
    }

    @Override
    public void info(String message) {
        this.writeLog(LogLevel.INFO, null, message, null, null);
    }

    @Override
    public void info(Throwable throwable) {
        this.writeLog(LogLevel.INFO, null, null, throwable, null);
    }

    @Override
    public void info(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.INFO, null, null, null, attrs);
    }

    @Override
    public void info(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.INFO, null, null, throwable, attrs);
    }

    @Override
    public void warn(String title, String message) {
        this.writeLog(LogLevel.WARN, title, null, null, null);
    }

    @Override
    public void warn(String title, Throwable throwable) {
        this.writeLog(LogLevel.WARN, title, null, throwable, null);
    }

    @Override
    public void warn(String title, String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.WARN, title, null, null, attrs);
    }

    @Override
    public void warn(String title, Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.WARN, title, null, throwable, attrs);
    }

    @Override
    public void warn(String message) {
        this.writeLog(LogLevel.WARN, null, message, null, null);
    }

    @Override
    public void warn(Throwable throwable) {
        this.writeLog(LogLevel.WARN, null, null, throwable, null);
    }

    @Override
    public void warn(String message, Map<String, String> attrs) {
        this.writeLog(LogLevel.WARN, null, null, null, attrs);
    }

    @Override
    public void warn(Throwable throwable, Map<String, String> attrs) {
        this.writeLog(LogLevel.WARN, null, null, throwable, attrs);
    }
}
