package com.yung.auto.framework.log.logger.entity;

import com.yung.auto.framework.log.listener.LoggerListener;
import com.yung.auto.framework.log.logger.enums.LoggerEnum;
import com.yung.auto.framework.log.logger.enums.LoggerLevelEnum;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;
import com.yung.auto.framework.log.logger.formate.LoggerFormat;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
@Data
public class LoggerEvent {
    private Map<String, String> tags = new HashMap<>();
    private Map<String, Long> ioIntervalMap;
    private Map<String, String> logContent;
    private Map<String, Object> logObject;
    private List<String> messages;
    private String title;
    private String logToken;
    private String logText;
    private LoggerSourceEnum source;
    private LoggerEnum loggerEnum;
    private LoggerLevelEnum loggerLevelEnum;
    private Exception exception;
    private List<LoggerFormat> formats;
    private List<LoggerListener> listeners;
    private long ioInterval;

    public void realWrite() {
        if (CollectionUtils.isEmpty(listeners)) {
            return;
        }
        listeners.forEach(listener -> {
            if (listener != null) {
                listener.write(this);
            }
        });
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogToken() {
        return logToken;
    }

    public void setLogToken(String logToken) {
        this.logToken = logToken;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public LoggerSourceEnum getSource() {
        return source;
    }

    public void setSource(LoggerSourceEnum source) {
        this.source = source;
    }

    public LoggerEnum getLoggerEnum() {
        return loggerEnum;
    }

    public void setLoggerEnum(LoggerEnum loggerEnum) {
        this.loggerEnum = loggerEnum;
    }

    public LoggerLevelEnum getLoggerLevelEnum() {
        return loggerLevelEnum;
    }

    public void setLoggerLevelEnum(LoggerLevelEnum loggerLevelEnum) {
        this.loggerLevelEnum = loggerLevelEnum;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public List<LoggerFormat> getFormats() {
        return formats;
    }

    public void setFormats(List<LoggerFormat> formats) {
        this.formats = formats;
    }

    public List<LoggerListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<LoggerListener> listeners) {
        this.listeners = listeners;
    }

    public long getIoInterval() {
        return ioInterval;
    }

    public void setIoInterval(long ioInterval) {
        this.ioInterval = ioInterval;
    }
}
