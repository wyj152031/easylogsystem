package com.yung.auto.framework.utility.collection;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public interface ValueCalc<T, V>  {
    /**
     * 计算
     * @param temp 前面的temp
     * @param item 此次循环的Item
     * @return
     */
    V getValue(V temp,T item);
}
