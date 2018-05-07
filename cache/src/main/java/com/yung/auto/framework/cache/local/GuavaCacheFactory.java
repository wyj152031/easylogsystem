package com.yung.auto.framework.cache.local;

import com.google.common.cache.CacheBuilder;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class GuavaCacheFactory {
    private Collection<String> cacheNames;
    private Collection<GuavaCache> caches;
    private boolean allowNullValues = true;
    private String spec;

    public void setName(String... names) {
        this.cacheNames = Arrays.asList(names);
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Collection<GuavaCache> getCaches() {
        createGuavaCaches();
        return this.caches;
    }

    private Collection<GuavaCache> createGuavaCaches() {
        if(cacheNames == null || cacheNames.isEmpty()) {
            return this.caches = Collections.EMPTY_LIST;
        }
        List<GuavaCache> result = new ArrayList<>();
        for(String name :cacheNames) {
            if(StringUtils.hasText(name)) {
                result.add(createGuavaCache(name));
            }
        }
        return result;
    }

    private GuavaCache createGuavaCache(String name) {
        if(StringUtils.hasText(spec)) {
            return new GuavaCache(CacheBuilder.from(spec).recordStats(),name,allowNullValues);
        } else {
            return new GuavaCache(name,allowNullValues);
        }
    }
}
