package com.yung.auto.framework.cache.local.cacherefresh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class CacheSignatureRegistery {

    private final Map<CacheSignatureKey, CacheInvocation> cachecdCacheInvocation = new ConcurrentHashMap<>();

    public void register(String[] cacheNames, Object key, CacheInvocation invocation) {
        CacheSignatureKey ckey = new CacheSignatureKey(cacheNames, key);
        if (!isExites(ckey)) {
            cachecdCacheInvocation.put(ckey, invocation);
        }
    }

    /**
     * 从缓存中查找Invocation
     * @param cacheNames
     * @param key
     * @return
     */
    public CacheInvocation getCacheSignature(String[] cacheNames, Object key) {
        return findByKey(new CacheSignatureKey(cacheNames, key));
    }

    /**
     * 根据cacheName、cacheKey查找Invocation
     * @param cacheName
     * @param key
     * @return
     */
    public CacheInvocation getCacheSignature(String cacheName, Object key) {
        for (Map.Entry<CacheSignatureKey, CacheInvocation> entry : cachecdCacheInvocation.entrySet()) {
            if (entry.getKey().isExitsCahceName(cacheName) && entry.getKey().keyEquals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 查找Invocation
     * @param ckey
     * @return
     */
    private CacheInvocation findByKey(CacheSignatureKey ckey) {
        for (Map.Entry<CacheSignatureKey, CacheInvocation> entry : cachecdCacheInvocation.entrySet()) {
            if (ckey != null && ckey.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean isExites(CacheSignatureKey ckey) {
        for (CacheSignatureKey k : cachecdCacheInvocation.keySet()) {
            if (k != null && k.equals(ckey)) {
                return true;
            }
        }
        return false;
    }

    public static class CacheSignatureKey {
        private String[] cacheNames;
        private Object key;

        public CacheSignatureKey(String[] cacheNames, Object key) {
            this.cacheNames = cacheNames;
            this.key = key;
        }

        public boolean isExitsCahceName(String cacheName) {
            if (cacheNames == null) {
                return false;
            }
            for (int idx = 0; idx < cacheNames.length; idx++) {
                if (cacheName != null && cacheName.equalsIgnoreCase(cacheNames[idx])) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 自定义equals
         *
         * @param ckey
         * @return
         */
        public boolean equals(CacheSignatureKey ckey) {
            return keyEquals(ckey) && cacheNameEquals(ckey);
        }

        /**
         * 比较key
         *
         * @param ckey
         * @return
         */
        private boolean keyEquals(CacheSignatureKey ckey) {
            return keyEquals(ckey.key);
        }

        public boolean keyEquals(Object ckey) {
            if (ckey == null && key == null) {
                return true;
            }
            return ckey != null && ckey.equals(key);
        }

        /**
         * 比较cacheNames
         *
         * @param ckey
         * @return
         */
        private boolean cacheNameEquals(CacheSignatureKey ckey) {
            if (key == null) {
                return false;
            }
            if (cacheNames == null && ckey.cacheNames == null) {
                return true;
            }
            if (cacheNames != null && ckey.cacheNames != null && cacheNames.length == ckey.cacheNames.length) {
                for (int idx = 0; idx < cacheNames.length; idx++) {
                    if (!cacheNames[idx].equalsIgnoreCase(ckey.cacheNames[idx])) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }
}
