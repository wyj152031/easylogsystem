package com.yung.auto.framework.utility.common;

import java.util.Collection;
import java.util.Map;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class CollectionUtils {

    public static <K,V> boolean hasElement(Map<K,V> map) {
        return map != null && map.size() > 0;
    }

    public static <T> boolean hasElement(Collection<T> list) {
        return list != null && list.size() > 0;
    }

    /**
     * 获取初始化map长度
     * @param len 长度
     * @return map长度
     */
    public static int getInitMapSize(int len) {
        int initLen = len + (len - (int) ((double) len * 0.65)) + 1;
        if (initLen < 16) {
            initLen = 16;
        } else {
            int parent = 0;
            for (int i = 4; i < 21; i++) {
                int v = 1 << i;
                if (initLen == v) {
                    break;
                } else if (initLen > parent && initLen < v) {
                    initLen = v;
                    break;
                }
                parent = v;
            }
        }
        if (initLen > 1000000) {
            initLen = 1000000;
        }
        return initLen;
    }
}
