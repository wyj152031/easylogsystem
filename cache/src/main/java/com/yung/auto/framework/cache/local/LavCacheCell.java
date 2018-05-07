package com.yung.auto.framework.cache.local;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public interface LavCacheCell {

    /**
     *  name of cache
     * @return
     */
    String getName();

    /**
     * Clear cache item by key
     *
     * @return
     */
    boolean clearByKey(Object key);

    /**
     * clear entire cache
     *
     * @return
     */
    boolean refresh();

    /**
     * fetch the keys of cache
     *
     * @return
     */
    Iterable keys();

    /**
     * fetch cache by key
     *
     * @param key
     * @return
     */
    Object getByKey(Object key);

    /**
     * the size of cache
     *
     * @return
     */
    long size();

    Object getCache();
}
