package com.yung.auto.framework.utility.entities;

import java.util.Map;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class EmptyLoggerImpl extends LogEntry implements ILogger {

    @Override
    public void addLogTag(String key, String value) {

    }

    @Override
    public void addLogTagMap(Map<String, String> logTags) {

    }

    @Override
    public void addLogContent(String key, String Content) {

    }

    @Override
    public void addLogObject(String key, Object obj) {

    }

    @Override
    public void setLogTitle(String title) {

    }

    @Override
    public void addMessages(String message) {

    }

    @Override
    public void write() {

    }

    @Override
    public ILogger cloneLogger(LoggerSourceEnum source) {
        return null;
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {

    }

    @Override
    public void setLogException(Exception ex) {

    }

    @Override
    public String getTagKey(String key) {
        return null;
    }

    @Override
    public Object getLogObject(String key) {
        return null;
    }

    @Override
    public void addIOInterval(String groupName, long interval) {

    }
}
