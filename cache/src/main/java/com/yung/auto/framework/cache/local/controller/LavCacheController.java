package com.yung.auto.framework.cache.local.controller;

import com.yung.auto.framework.cache.local.LavCacheCell;
import com.yung.auto.framework.cache.local.LavCacheManager;
import com.yung.auto.framework.cache.local.cacherefresh.CacheCacheableSignatureAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/cache")
public class LavCacheController implements CacheController{

    private final String FAILURE_NOT_CONTAINS_KEY = "There is no such key in cache.";
    private final String SUCCESS = "Success";
    private final String FAILURE = "Failure";

    @Autowired
    private CacheCacheableSignatureAdvice advice;
    /**
     * list Cache keys
     *
     * @param cacheName
     * @param host
     * @return
     */
    @RequestMapping(value = "listkeys.do", method = {RequestMethod.GET, RequestMethod.POST})
    @Override
    public CacheResponse listCacheKeys(@RequestParam(value = "cachename", required = false) String cacheName,
                                       @RequestParam(value = "host") String host) {
        if (!StringUtils.hasText(cacheName)) {
            Map<String, List<Object>> cahces = new HashMap<>();
            for (String key : LavCacheManager.getCaches().keySet()) {
                cahces.put(key, transCacheKeys(LavCacheManager.getCaches().get(key)));
            }
            return response(CacheResponseCode.SUCCESS, SUCCESS, cahces);
        }
        if (!LavCacheManager.getCaches().containsKey(cacheName)) {
            return response(CacheResponseCode.FAILURE_NOT_CONTAINS_KEY, FAILURE_NOT_CONTAINS_KEY, null);
        } else {
            return response(CacheResponseCode.SUCCESS, SUCCESS, LavCacheManager.getCaches().get(cacheName).keys());
        }
    }

    /**
     * View the cache
     *
     * @param cacheName
     * @param host
     * @return
     */
    @RequestMapping(value = "view.do", method = {RequestMethod.GET, RequestMethod.POST})
    @Override
    public CacheResponse viewCacheByCacheName(@RequestParam(value = "cachename") String cacheName,
                                              @RequestParam(value = "key") String cacheKey,
                                              @RequestParam("host") String host) {
        if (LavCacheManager.getCaches().containsKey(cacheName)) {
            LavCacheCell cell = LavCacheManager.getCaches().get(cacheName);
            if (StringUtils.hasText(cacheKey) && cell != null) {
                Object value=cell.getByKey(cacheKey);
                if(value==null){
                    return response(CacheResponseCode.FAILURE_NO_RESULT,FAILURE,null);
                }
                return response(CacheResponseCode.SUCCESS, SUCCESS, cell.getByKey(cacheKey));
            }
            return response(CacheResponseCode.SUCCESS, null, transCache(LavCacheManager.getCaches().get(cacheName)));
        } else {
            return response(CacheResponseCode.FAILURE_NOT_CONTAINS_KEY, FAILURE_NOT_CONTAINS_KEY, null);
        }
    }

    /**
     * Refresh the cache
     *
     * @param cacheName
     * @param cacheKey
     * @param host
     * @return
     */
    @RequestMapping(value = "refresh.do", method = {RequestMethod.GET, RequestMethod.POST})
    @Override
    public CacheResponse refreshCacheByCacheName(@RequestParam(value = "cachename") String cacheName,
                                                 @RequestParam(value = "key") String cacheKey,
                                                 @RequestParam(value = "host") String host) {

        LavCacheCell cell = LavCacheManager.getCaches().get(cacheName);
        if (cell == null) {
            return response(CacheResponseCode.FAILURE_NOT_CONTAINS_KEY, FAILURE_NOT_CONTAINS_KEY, null);
        }
        if (!transCacheKeys(cell).contains(cacheKey)) {
            return response(CacheResponseCode.FAILURE_NOT_CONTAINS_KEY, FAILURE_NOT_CONTAINS_KEY, null);
        }
        int rcode = advice.update(cacheName, cacheKey);
        switch (rcode) {
            case 0:
                return response(CacheResponseCode.SUCCESS, SUCCESS, null);
            default:
                return response(CacheResponseCode.FAILURE, String.valueOf(rcode), null);
        }
    }

    /**
     * Trans Cache entities
     *
     * @param cell
     * @return
     */
    private Map<String, Object> transCache(LavCacheCell cell) {
        Map<String, Object> cache = new HashMap<>();
        Iterable itable = cell.keys();
        if (itable == null) {
            return cache;
        }
        Iterator keys = itable.iterator();
        while (keys.hasNext()) {
            Object key = keys.next();
            cache.put(key.toString(), cell.getByKey(key));
        }
        return cache;
    }

    /**
     * Trans Cache keys
     *
     * @param cell
     * @return
     */
    private List<Object> transCacheKeys(LavCacheCell cell) {
        List<Object> cache = new ArrayList<>();
        Iterable itable = cell.keys();
        if (itable == null) {
            return cache;
        }
        Iterator keys = itable.iterator();
        while (keys.hasNext()) {
            Object key = keys.next();
            cache.add(key);
        }
        return cache;
    }

    private CacheResponse response(long code, String msg, Object v) {
        return new CacheResponse(code, msg, v);
    }
}
