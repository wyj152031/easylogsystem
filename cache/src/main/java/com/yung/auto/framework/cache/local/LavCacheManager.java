package com.yung.auto.framework.cache.local;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class LavCacheManager {
    private static Map<String, LavCacheCell> caches = new ConcurrentHashMap<>();

    public static boolean add(LavCacheCell entity) {
        boolean flag = false;
        if (StringUtils.hasText(entity.getName()) && caches.containsKey(entity.getName())) {
            caches.put(entity.getName(), entity);
            flag = true;
        }
        return flag;
    }

    public static Map<String,LavCacheCell> getCaches()  {
        return caches;
    }
}
