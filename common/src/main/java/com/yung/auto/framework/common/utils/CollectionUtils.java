package com.yung.auto.framework.common.utils;

import java.util.*;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class CollectionUtils {
    public static <T> boolean hasElement(Collection<T> list) {
        return list != null && list.size() > 0;
    }

    public static <T> boolean hasElement(T[] list) {
        return list != null && list.length > 0;
    }

    public static <K, V> boolean hasElement(Map<K, V> map) {
        return map != null && map.size() > 0;
    }

    /**
     * 去除重复元素
     *
     * @param list
     * @param <T>
     */
    public static <T> void removeDuplicate(Collection<T> list) {
        if (!hasElement(list)) return;
        Set<T> sets = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(sets);
    }

    /**
     * 用指定key连接集合
     *
     * @param key  连接key
     * @param strs list
     * @return String
     */
    public static String strJoin(String key, List<String> strs) {
        if (strs == null || strs.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int len = strs.size();
        int index = 0;
        for (String s : strs) {
            if (index < len - 1 && len > 1) {
                builder.append(s + key);
            } else {
                builder.append(s);
            }
            index++;
        }
        return builder.toString();
    }

    /**
     * 获取初始化map长度
     *
     * @param len 长度
     * @return map长度
     */
    public static int getInitMapSize(int len) {
        int tableSize = tableSizeFor(len);
        int realSize = (int) (tableSize * DEFAULT_LOAD_FACTOR);
        if (realSize < len) {
            tableSize = tableSize << 1;
        }
        return tableSize;
    }

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Returns a power of two size for the given target capacity.
     */
    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
