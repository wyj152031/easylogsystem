package com.yung.auto.framework.cache.local;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class GuavaCacheManager extends AbstractTransactionSupportingCacheManager {
    private Collection<Cache> caches;
    private String spec;
    private volatile CacheBuilder cacheBuilder;
    private boolean allowNullValues = true;

    @Override
    public void initializeCaches() {
        super.initializeCaches();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null) {
            cache = createGuavaCache(name);
            synchronized (this) {
                if (cache != null) {
                    addCache(cache);
                }
            }
        }
        if (!LavCacheManager.getCaches().containsKey(cache)) {
            Object nativeCache = cache.getNativeCache();
            if (nativeCache != null && nativeCache instanceof com.google.common.cache.Cache) {
                LavCacheManager.add(new SimpleLavCacheCell(name, (com.google.common.cache.Cache) nativeCache));
            }
        }
        return super.getCache(name);
    }

    private GuavaCache createGuavaCache(String name) {
        return new GuavaCache(getCacheBuilder(),name, allowNullValues);
    }

    private CacheBuilder getCacheBuilder() {
        if (cacheBuilder == null) {
            synchronized (this) {
                if (cacheBuilder == null) {
                    if (StringUtils.hasText(spec)) {
                        cacheBuilder = CacheBuilder.from(spec).recordStats();
                    } else {
                        cacheBuilder = CacheBuilder.newBuilder().recordStats();
                    }
                }
                notify();
            }
        }
        return cacheBuilder;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return caches == null ? Collections.emptyList() : caches;
    }

    public Collection<Cache> getCaches() {
        return caches;
    }

    public void setCaches(Collection<Cache> caches) {
        this.caches = caches;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setCacheBuilder(CacheBuilder cacheBuilder) {
        this.cacheBuilder = cacheBuilder;
    }

    public boolean isAllowNullValues() {
        return allowNullValues;
    }

    public void setAllowNullValues(boolean allowNullValues) {
        this.allowNullValues = allowNullValues;
    }
}
