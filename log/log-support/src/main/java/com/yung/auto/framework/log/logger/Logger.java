package com.yung.auto.framework.log.logger;

import com.yung.auto.framework.log.logger.enums.LoggerLevelEnum;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;
import com.yung.auto.framework.log.logger.enums.LoggerEnum;

import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface Logger {

    void addLogTag(String key, String value);

    void addLogTagMap(Map<String, String> logTags);

    void addLogContent(String key, String Content);

    void addLogObject(String key, Object obj);

    void setLogTitle(String title);


    void addMessages(String message);

    void write();

    Logger cloneLog(LoggerSourceEnum source, LoggerEnum loggerEnum);

    void setLogLevel(LoggerLevelEnum logLevel);

    void setLogException(Exception ex);

    String getTagKey(String key);

    Object getLogObject(String key);

    void addIOInterval(String groupName, long interval);
}
