package com.yung.auto.framework.utility.collection;

import java.util.*;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public class CollectionUtils {

    /**
     * 过滤出符合条件的元素
     *
     * @param list    待过滤的数组
     * @param matcher 条件筛选器
     * @param <T>     元素类型
     * @return 返回符合条件的元素数组，无符合条件时返回空数组
     */
    public static <T> List<T> filter(Collection<T> list, ObjectMatcher<T> matcher) {
        if (null == list || null == matcher) {
            return new ArrayList<>();
        }

        List<T> temp = new ArrayList<T>();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (matcher.isMatch(item)) {
                temp.add(item);
            }
        }

        return temp;
    }

    /**
     * 查找第一个符合条件的元素
     *
     * @param list    待过滤的数组
     * @param matcher 条件筛选器
     * @param <T>     元素类型
     * @return 返回查找到的第一个符合条件的元素，无符合条件时返回null
     */
    public static <T> T find(Collection<T> list, ObjectMatcher<T> matcher) {
        if (null == list || null == matcher) return null;

        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (matcher.isMatch(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 判断是否存在符合条件的元素
     *
     * @param list    待过滤的数组
     * @param matcher 条件筛选器
     * @param <T>     元素类型
     * @return 存在符合条件的元素返回true，否则返回false
     */
    public static <T> boolean exist(Collection<T> list, ObjectMatcher<T> matcher) {
        if (null == list || null == matcher) return false;

        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (matcher.isMatch(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断集合元素是否都符合条件
     *
     * @param list    待过滤的数组
     * @param matcher 条件筛选器
     * @param <T>     元素类型
     * @return 集合元素全部符合条件返回true，否则返回false
     */
    public static <T> boolean all(Collection<T> list, ObjectMatcher<T> matcher) {
        if (null == list || null == matcher) return false;

        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (!matcher.isMatch(item)) {
                return false;
            }
        }
        return true;
    }

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

//    public static <T,V extends Number> int sum(Collection<T> list, ValueSelector<T, V> selector) {
//        Iterator<T> it = list.iterator();
//        Integer sum = new Integer(0);
//        V v=new V();
//
//        while (it.hasNext()) {
//            T item = it.next();
//            sum += selector.getValue(item);
//        }
//        return sum.intValue();
//    }

    /**
     * 聚合List的值
     *
     * @param list
     * @param valueCalc
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> V sum(Collection<T> list, ValueCalc<T, V> valueCalc) {
        if (hasElement(list)) {
            V v = null;
            int index = 0;
            for (T t : list) {
                v = valueCalc.getValue(v, t);
            }
            return v;
        }
        return null;
    }

    /**
     * 将列表转为map
     *
     * @param list
     * @param mapMatcher
     * @param <K>        key的类型
     * @param <V>        value的类型
     * @param maps       默认使用hashmap，如果需要用其他map，new好传入
     * @return
     */
    public static <K, V> Map<K, List<V>> toMapList(Collection<V> list, MapMatcher<K, V> mapMatcher, Map<K, List<V>> maps) {
        if (hasElement(list)) {
            if (maps == null) {
                maps = new HashMap<>();
            }
            for (V t : list) {
                K key = mapMatcher.getKey(t);
                List<V> values = maps.get(key);
                if (!hasElement(values)) {
                    values = new ArrayList<>();
                    maps.put(key, values);
                }
                values.add(t);
            }
            return maps;
        }
        return null;
    }

    /**
     * 将列表转为map
     *
     * @param list       Collection<V>
     * @param mapMatcher MapMatcher<K, V>
     * @param <K>        key的类型
     * @param <V>        value的类型
     * @return 转换好的map
     */
    public static <K, V> Map<K, List<V>> toMapList(Collection<V> list, MapMatcher<K, V> mapMatcher) {
        return toMapList(list, mapMatcher, null);
    }

    /**
     * 将列表转为map
     *
     * @param list       Collection<V>
     * @param mapMatcher MapMatcher<K, V>
     * @param <K>        key的类型
     * @param <V>        value的类型
     * @param maps       默认使用hashmap，如果需要用其他map，new好传入
     * @return 转换好的map
     */
    public static <K, V> Map<K, V> toMap(Collection<V> list, MapMatcher<K, V> mapMatcher, Map<K, V> maps) {
        if (hasElement(list)) {
            if (maps == null) {
                maps = new HashMap<>();
            }
            for (V t : list) {
                K key = mapMatcher.getKey(t);
                if (maps.containsKey(key)) {
                    continue;
                }
                maps.put(key, t);
            }
            return maps;
        }
        return null;
    }

    /**
     * 将列表转为map
     *
     * @param list       Collection<V>
     * @param mapMatcher MapMatcher<K, V>
     * @param <K>        key的类型
     * @param <V>        value的类型
     * @return 转换好的map
     */
    public static <K, V> Map<K, V> toMap(Collection<V> list, MapMatcher<K, V> mapMatcher) {
        return toMap(list, mapMatcher, null);
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
