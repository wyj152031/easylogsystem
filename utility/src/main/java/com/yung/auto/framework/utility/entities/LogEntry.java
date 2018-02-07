package com.yung.auto.framework.utility.entities;

import com.yung.auto.framework.utility.formate.ILoggerFormat;
import com.yung.auto.framework.utility.listenter.ILoggerListener;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @autor wangyujing
 * @since 2018/1/26.
 */
public class LogEntry {
    private Map<String, String> logTag;
    private Map<String, Long> ioIntervalMap;
    private Map<String, String> logContent;
    private Map<String, Object> logObject;
    private List<String> messages;
    private String logToken;
    private String title;
    private LoggerSourceEnum source;
    private String logText;
    private LogLevel loggerLevel;
    private Exception exception;
    private List<ILoggerListener> loggerListener;
    private List<ILoggerFormat> loggerFormats;
    private long ioInterval;

    public void realWrite() {
        if(loggerListener != null) {
            Iterator<ILoggerListener>  loggerListeners = loggerListener.iterator();
            while(loggerListeners.hasNext()) {
                ILoggerListener loggerListener = loggerListeners.next();
                if(loggerListener !=  null) {
                    loggerListener.write(this);
                }
            }
        }
    }

    public Map<String, String> getLogTag() {
        return logTag;
    }

    public void setLogTag(Map<String, String> logTag) {
        this.logTag = logTag;
    }

    public Map<String, Long> getIoIntervalMap() {
        return ioIntervalMap;
    }

    public void setIoIntervalMap(Map<String, Long> ioIntervalMap) {
        this.ioIntervalMap = ioIntervalMap;
    }

    public Map<String, String> getLogContent() {
        return logContent;
    }

    public void setLogContent(Map<String, String> logContent) {
        this.logContent = logContent;
    }

    public Map<String, Object> getLogObject() {
        return logObject;
    }

    public void setLogObject(Map<String, Object> logObject) {
        this.logObject = logObject;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getLogToken() {
        return logToken;
    }

    public void setLogToken(String logToken) {
        this.logToken = logToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LoggerSourceEnum getSource() {
        return source;
    }

    public void setSource(LoggerSourceEnum source) {
        this.source = source;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public LogLevel getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(LogLevel loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public List<ILoggerListener> getLoggerListener() {
        return loggerListener;
    }

    public void setLoggerListener(List<ILoggerListener> loggerListener) {
        this.loggerListener = loggerListener;
    }

    public List<ILoggerFormat> getLoggerFormats() {
        return loggerFormats;
    }

    public void setLoggerFormats(List<ILoggerFormat> loggerFormats) {
        this.loggerFormats = loggerFormats;
    }

    public long getIoInterval() {
        return ioInterval;
    }

    public void setIoInterval(long ioInterval) {
        this.ioInterval = ioInterval;
    }
}
