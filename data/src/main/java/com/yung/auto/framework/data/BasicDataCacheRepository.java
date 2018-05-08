package com.yung.auto.framework.data;

import java.util.List;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public interface BasicDataCacheRepository<T> {

    List<T> getArrayData(String key);

    T getData(String key);

    void remove(String key);
}
