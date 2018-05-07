package com.yung.auto.framework.cache.local.cacherefresh;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public interface CacheInvocation {

    Object invoke(Object key) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException;
}
