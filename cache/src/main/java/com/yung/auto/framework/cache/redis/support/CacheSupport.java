package com.yung.auto.framework.cache.redis.support;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by wangyj on 2018/6/18.
 */
public interface CacheSupport {

    /**
     * 注册缓存方法信息
     *
     * @param invokedBean          代理Bean
     * @param invokedMethod        代理方法名称
     * @param invocationParamTypes 代理方法参数类型
     * @param invocationArgs       代理方法参数
     * @param cacheNames           缓存名称（@Cacheable注解的value）
     * @param cacheKey             缓存key（@Cacheable注解的key）
     */
    void registerInvocation(Object invokedBean, Method invokedMethod, Class[] invocationParamTypes, Object[] invocationArgs, Set<String> cacheNames, String cacheKey);

    /**
     * 按容器以及指定键更新缓存
     *
     * @param cacheName
     * @param cacheKey
     */
    void refreshCacheByKey(String cacheName, String cacheKey);
}
