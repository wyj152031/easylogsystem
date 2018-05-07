package com.yung.auto.framework.cache.local.cacheinit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class CacheInitServiceHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         context = applicationContext;
    }

    public static <T> Map<String,T> getBeansOfType(Class<T> clazz) {
        return context.getBeansOfType(clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
