package com.yung.auto.framework.utility.trace;

import com.yung.auto.framework.utility.agent.LogManager;
import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;

import java.util.Map;

/**
 * Created by wangyujing on 2018/1/26.
 */
public class CommonTracer implements ITrace {
    private CLoggingLogger logger;
    private String name;

    public CommonTracer(String name) {
        if(Strings.isNullOrEmpty(name)) {
            this.name = "defaultTraceName";
        } else {
            this.name = name;
        }
        this.logger = (CLoggingLogger) LogManager.getLogger(name);
    }

    public boolean isTracing() {
        return false;
    }

    public void clear() {
        return;
    }

    private void log(LogType logType, String title, String message, LogLevel logLevel, Map<String, String> attrs, Throwable throwable) {
        logger.writeLog(logLevel, logType, title, message, throwable, attrs);
    }

    public void log(LogType type, LogLevel level, String title, String message) {
        this.log(type, title, message, level, null, null);
    }

    public void log(LogType type, LogLevel level, String title, Throwable t) {
        String msg = "NullThrowable";
        this.log(type, title, msg, level, null, t);
    }

    public void log(LogType type, LogLevel level, String title, String message, Map<String, String> attrs) {
        this.log(type, title, message, level, attrs, null);
    }

    public void log(LogType type, LogLevel level, String title, Throwable throwable, Map<String, String> attrs) {
        String msg = "NullThrowable";
        this.log(type, title, msg, level, attrs, null);
    }

    public void log(LogType type, LogLevel level, String message) {
        this.log(type, null, message, level, null, null);
    }

    public void log(LogType type, LogLevel level, Throwable t) {
        String msg = "NullThrowable";
        this.log(type, null, msg, level, null, t);
    }

    public void log(LogType type, LogLevel level, String message, Map<String, String> attrs) {
        this.log(type, null, message, level, attrs, null);
    }

    public void log(LogType type, LogLevel level, Throwable throwable, Map<String, String> attrs) {
        String msg = "NullThrowable";
        this.log(type, null, msg, level, attrs, null);
    }
}
