package com.yung.auto.framework.log.logger.entity;


import com.yung.auto.framework.log.logger.Logger;
import com.yung.auto.framework.log.logger.enums.LoggerEnum;
import com.yung.auto.framework.log.logger.enums.LoggerLevelEnum;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;

import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class EmptyLoggerEventImpl extends LoggerEvent implements Logger {

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
    public Logger cloneLog(LoggerSourceEnum source, LoggerEnum loggerEnum) {
        return null;
    }

    @Override
    public void setLogLevel(LoggerLevelEnum logLevel) {

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

    @Override
    public void write() {

    }
}
