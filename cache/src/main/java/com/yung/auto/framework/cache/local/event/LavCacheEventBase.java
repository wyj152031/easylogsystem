package com.yung.auto.framework.cache.local.event;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class LavCacheEventBase implements LavCacheEvent {
    protected EventType event;

    public LavCacheEventBase(EventType eventType) {
        this.event = eventType;
    }

    @Override
    public EventType getEvent() {
        return this.event;
    }
}
