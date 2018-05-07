package com.yung.auto.framework.cache.local.controller;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public interface CacheController {
    CacheResponse listCacheKeys(String cacheName, String host);

    CacheResponse viewCacheByCacheName(String cacheName, String cacheKey, String host);

    CacheResponse refreshCacheByCacheName(String cacheName, String cacheKey, String host);
}
