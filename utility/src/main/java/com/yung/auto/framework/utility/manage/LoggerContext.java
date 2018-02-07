package com.yung.auto.framework.utility.manage;

import com.yung.auto.framework.utility.entities.ILogger;

import java.util.HashMap;
import java.util.Map;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class LoggerContext {

    private static ThreadLocal<ILogger> loggerContext = new ThreadLocal<>();
    private static ThreadLocal<Map<String,Object>> mapThreadLocal = new ThreadLocal<>();

    public static ILogger getLogger() {
        return loggerContext.get();
    }

    public static void setLogger(ILogger logger) {
        loggerContext.set(logger);
    }

    public static void removeLogger() {
        loggerContext.remove();
    }

    public static void set(String key,String value) {
        if(mapThreadLocal.get() != null) {
            mapThreadLocal.set(new HashMap<>());
        }
        mapThreadLocal.get().put(key,value);
    }

    public static <T> T get(String key) {
        if(mapThreadLocal.get() != null) {
            return (T) mapThreadLocal.get().get(key);
        }
        return null;
    }

}
