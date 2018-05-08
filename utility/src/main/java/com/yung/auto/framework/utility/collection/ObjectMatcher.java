package com.yung.auto.framework.utility.collection;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public interface ObjectMatcher<T> {
    boolean isMatch(T item);
}
