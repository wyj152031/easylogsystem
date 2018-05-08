package com.yung.auto.framework.utility.entities;

import com.yung.auto.framework.utility.collection.CollectionUtils;
import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.listenter.ILoggerListener;
import com.yung.auto.framework.utility.formate.ILoggerFormat;
import com.yung.auto.framework.utility.manage.LoggerContext;
import com.yung.auto.framework.utility.tags.LoggerStatics;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class DefaultLoggerImpl extends LogEntry implements ILogger {

    public DefaultLoggerImpl(String logToken, LoggerSourceEnum logSource, List<ILoggerListener> loggerListeners, List<ILoggerFormat> loggerFormats) {
        setLogToken(logToken);
        setSource(logSource);
        setLoggerListener(loggerListeners);
        setLoggerFormats(loggerFormats);
    }

    @Override
    public void addLogTag(String key, String value) {
        if (getLogTag() == null) {
            setLogTag(new ConcurrentHashMap<String, String>(CollectionUtils.getInitMapSize(80)));
        }

        if (Strings.isNullOrEmpty(key)) {
            return;
        }
        if (Strings.isNullOrEmpty(value)) {
            value = "-";
        }

        String tempKey = key.toLowerCase();
        if (LoggerStatics.hasWhiteProperties(tempKey)) {
            key = tempKey;
            ILogger logger = LoggerContext.getLogger();
            if (logger != null && logger != this) {
                logger.addLogTag(key, value);
            }
        }
        getLogTag().put(key, value);
    }

    @Override
    public void addLogTagMap(Map<String, String> logTags) {
        if (CollectionUtils.hasElement(logTags)) {
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
            setLogContent(new ConcurrentHashMap<>(CollectionUtils.getInitMapSize(16)));
        }
        if (Strings.isNullOrEmpty(content)) {
            content = "-";
        }
        getLogContent().put(key, content);
    }

    @Override
    public void addLogObject(String key, Object obj) {
        if (getLogObject() == null) {
            setLogObject(new IgnoreNullMap<>(new ConcurrentHashMap<>()));
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
    public ILogger cloneLogger(LoggerSourceEnum source) {
        DefaultLoggerImpl logger = new DefaultLoggerImpl(getLogToken(), source, getLoggerListener(), getLoggerFormats());

        Map<String, String> clonedTag = new ConcurrentHashMap<>(getLogTag());
        logger.setLogTag(clonedTag);

        return logger;
    }

    @Override
    public void setLogLevel(LogLevel loggerLevel) {
        setLoggerLevel(loggerLevel);
    }

    @Override
    public void setLogException(Exception exception) {
        setException(exception);
    }

    @Override
    public String getTagKey(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        if (getLogTag() != null) {
            return getLogTag().get(key);
        }
        return null;
    }

    @Override
    public Object getLogObject(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        if (getLogObject() != null) {
            return getLogObject().get(key);
        }
        return null;
    }

    @Override
    public void addIOInterval(String groupName, long interval) {
        if (Strings.isNullOrEmpty(groupName)) {
            setIoInterval(getIoInterval() + interval);
        } else {
            addGroupInterval(groupName, interval);
        }
    }

    public void addGroupInterval(String groupName, long interval) {
        synchronized (DefaultLoggerImpl.class) {
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

    @Override
    public void write() {
        LoggerThreadPool.put(this);
    }

    private class IgnoreNullMap<K, V> implements Map<K, V> {

        private final Map<K, V> map;

        public IgnoreNullMap(Map<K, V> map) {
            this.map = map;
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return key != null && this.map.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return value != null && this.map.containsValue(value);
        }

        @Override
        public V get(Object key) {
            return key == null ? null : this.map.get(key);
        }

        @Override
        public V put(K key, V value) {
            return key == null || value == null ? null : this.map.put(key, value);
        }

        @Override
        public V remove(Object key) {
            return key == null ? null : this.map.remove(key);
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            this.map.putAll(m);
        }

        @Override
        public void clear() {
            this.map.clear();
        }

        @Override
        public Set<K> keySet() {
            return this.map.keySet();
        }

        @Override
        public Collection<V> values() {
            return this.map.values();
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return this.map.entrySet();
        }
    }
}
