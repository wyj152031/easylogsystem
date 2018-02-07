package com.yung.auto.framework.utility.entities;

import java.util.Map;

/**
 * Created by wangyujing on 2018/1/26.
 */
public interface ILogger {
    void addLogTag(String key, String value);

    void addLogTagMap(Map<String, String> logTags);

    void addLogContent(String key, String Content);

    void addLogObject(String key, Object obj);

    void setLogTitle(String title);


    void addMessages(String message);

    void write();

    ILogger cloneLogger(LoggerSourceEnum source);

    void setLogLevel(LogLevel logLevel);

    void setLogException(Exception ex);

    String getTagKey(String key);

    Object getLogObject(String key);

    void addIOInterval(String groupName, long interval);
}
