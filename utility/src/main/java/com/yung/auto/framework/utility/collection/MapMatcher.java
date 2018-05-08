package com.yung.auto.framework.utility.collection;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public interface MapMatcher<K, V> {
    K getKey(V item);
}
