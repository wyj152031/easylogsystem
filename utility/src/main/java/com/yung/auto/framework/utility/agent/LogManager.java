package com.yung.auto.framework.utility.agent;

import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.trace.CLoggingLogger;
import com.yung.auto.framework.utility.trace.ILog;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangyujing on 2018/1/26.
 */
public class LogManager {
    private static ConcurrentHashMap<String, ILog> _loggers = new ConcurrentHashMap<String, ILog>();
    /**
     * Initializes a new instance of the <see cref="LogManager" /> class.
     * <p/>
     * Uses a private access modifier to prevent instantiation of this class.
     */
    private LogManager() {
    }

    /**
     * 通过类型名获取ILog实例。
     *
     * @param type logger type
     * @return ILog instance
     */
    public static ILog getLogger(Class<?> type) {
        if (type == null) {
            return getLogger("defaultLogger");
        } else {
            return getLogger(type.getName());
        }
    }

    /**
     * 通过字符串名获取ILog实例。
     *
     * @param name logger name
     * @return ILog instance
     */
    public static ILog getLogger(String name) {
        String loggerName = name;
        if (Strings.isNullOrEmpty(name)) {
            loggerName = "defaultLogger";
        }
        ILog logger = _loggers.get(loggerName);
        if (logger == null) {
            ILog newLogger = new CLoggingLogger(loggerName);
            logger = _loggers.putIfAbsent(loggerName, newLogger);
            if (logger == null) {
                logger = newLogger;
            }
        }
        return logger;
    }
}

