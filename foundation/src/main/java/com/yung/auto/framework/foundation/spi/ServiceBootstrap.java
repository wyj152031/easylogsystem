package com.yung.auto.framework.foundation.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class ServiceBootstrap {

    public static <S> S loadFirst(Class<S> clazz) {
        Iterator<S> iterator = loadAll(clazz);
        if(!iterator.hasNext()) {
            throw new IllegalStateException(String.format("No implementation defined in /META-INF/services/%s, please check whether the file exists and has the right implementation class!", new Object[]{clazz.getName()}));
        } else {
            return iterator.next();
        }
    }

    private static <S> Iterator<S> loadAll(Class<S> clazz) {
        ServiceLoader<S> loader = ServiceLoader.load(clazz);
        return loader.iterator();
    }
}
