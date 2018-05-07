package com.yung.auto.framework.cache.local.event;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class LavCacheEventSubject {

    @Autowired(required = false)
    List<LavCacheListener> listeners;

    public void registerListener(LavCacheListener listener) {
        if (null == listeners) {
            listeners = new ArrayList<>();
        }
        listeners.add(listener);
    }

    public void notifyListeners(LavCacheEvent event) {
        if (listeners == null) {
            return;
        }
        for (LavCacheListener listener : listeners) {
            listener.fire(event);
        }
    }
}
