package com.yung.auto.framework.cache.local;

import com.google.common.cache.Cache;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class SimpleLavCacheCell implements LavCacheCell {
    private final String name;
    private final Cache nativeCache;

    public SimpleLavCacheCell(String name, Cache cache) {
        this.name = name;
        this.nativeCache = cache;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean clearByKey(Object key) {
        this.nativeCache.invalidate(key);
        return true;
    }

    @Override
    public boolean refresh() {
        this.nativeCache.invalidateAll();
        return true;
    }

    @Override
    public Iterable keys() {
        return this.nativeCache.asMap().keySet();
    }

    @Override
    public Object getByKey(Object key) {
        return this.nativeCache.getIfPresent(key);
    }

    @Override
    public long size() {
        return this.nativeCache.size();
    }

    @Override
    public Object getCache() {
        return this.nativeCache;
    }
}
