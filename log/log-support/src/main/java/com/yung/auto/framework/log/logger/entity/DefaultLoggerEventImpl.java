package com.yung.auto.framework.log.logger.entity;


import com.yung.auto.framework.log.executor.LoggerThreadPool;
import com.yung.auto.framework.log.listener.LoggerListener;
import com.yung.auto.framework.log.logger.Logger;
import com.yung.auto.framework.log.logger.LoggerConstants;
import com.yung.auto.framework.log.logger.enums.LoggerEnum;
import com.yung.auto.framework.log.logger.enums.LoggerLevelEnum;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;
import com.yung.auto.framework.log.logger.formate.LoggerFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DefaultLoggerEventImpl extends LoggerEvent implements Logger {

    public DefaultLoggerEventImpl(String logToken,
                                  LoggerSourceEnum logSource,
                                  LoggerEnum loggerEnum,
                                  List<LoggerListener> loggerListeners,
                                  List<LoggerFormat> loggerFormats) {
        setLogToken(logToken);
        setSource(logSource);
        setListeners(loggerListeners);
        setFormats(loggerFormats);
        setLoggerEnum(loggerEnum);
    }


    @Override
    public void addLogTag(String key, String value) {
        if (getTags() == null) {
            setTags(new ConcurrentHashMap<String, String>(80));
        }

        if (StringUtils.isBlank(key)) {
            return;
        }
        if (StringUtils.isBlank(value)) {
            value = "-";
        }

        getTags().put(key, value);
    }

    @Override
    public void addLogTagMap(Map<String, String> logTags) {
        if (!CollectionUtils.isEmpty(logTags)) {
            Iterator iterator = logTags.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> tag = (Map.Entry<String, String>) iterator.next();
                addLogTag(tag.getKey(), tag.getValue());
            }
        }
    }

    @Override
    public void addLogContent(String key, String content) {
        if (getLogContent() == null) {
            setLogContent(new ConcurrentHashMap<>(16));
        }
        if (StringUtils.isBlank(content)) {
            content = "-";
        }
        getLogContent().put(key, content);
    }

    @Override
    public void addLogObject(String key, Object obj) {
        if (getLogObject() == null) {
            setLogObject(new ConcurrentHashMap<>());
        }
        if(obj == null) {
            obj = "";
        }
        getLogObject().put(key, obj);
    }

    @Override
    public void setLogTitle(String title) {
        setTitle(title);
    }

    @Override
    public void addMessages(String message) {
        if (getMessages() == null) {
            setMessages(new ArrayList<>());
        }
        getMessages().add(message);
    }

    @Override
    public Logger cloneLog(LoggerSourceEnum source, LoggerEnum loggerEnum) {
        DefaultLoggerEventImpl logger = new DefaultLoggerEventImpl(getLogToken(), source,
                loggerEnum, getListeners(), getFormats());

        Map<String, String> clonedTag = new ConcurrentHashMap<>();
        getTags().forEach((k, v) -> {
            if (LoggerConstants.REMOVE_TAGS_LIST.contains(k)) {
                return;
            }
            clonedTag.put(k, v);
        });
        logger.setTags(clonedTag);

        return logger;
    }

    @Override
    public void setLogLevel(LoggerLevelEnum logLevel) {
        setLoggerLevelEnum(logLevel);
    }

    @Override
    public void setLogException(Exception ex) {
        setException(ex);
    }

    @Override
    public String getTagKey(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        if (getTags() != null) {
            return getTags().get(key);
        }
        return null;
    }

    @Override
    public Object getLogObject(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        if (getLogObject() != null) {
            return getLogObject().get(key);
        }
        return null;
    }

    @Override
    public void addIOInterval(String groupName, long interval) {
        if (StringUtils.isBlank(groupName)) {
            setIoInterval(getIoInterval() + interval);
        } else {
            addGroupInterval(groupName, interval);
        }
    }

    private void addGroupInterval(String groupName, long interval) {
        synchronized (DefaultLoggerEventImpl.class) {
            Map<String, Long> map = getIoIntervalMap();
            Long originInterval = interval;
            if (getIoIntervalMap() == null) {
                map = new ConcurrentHashMap<>();
                setIoIntervalMap(map);
            } else {
                originInterval = map.get(groupName);
                if (originInterval == null || originInterval < interval) {
                    originInterval = interval;
                }
            }
            getIoIntervalMap().put(groupName, originInterval);
        }
    }

    public void write() {
        LoggerThreadPool.put(this);
    }
}
