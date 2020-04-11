package com.yung.auto.framework.log.logger.context;

import com.yung.auto.framework.log.logger.Logger;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class LoggerContext {

    private static ThreadLocal<Logger> LOGGER_CONTEXT = new ThreadLocal<>();

    public static void setLogger(Logger logger) {
        LOGGER_CONTEXT.set(logger);
    }

    public static void remove() {
        LOGGER_CONTEXT.remove();
    }

    public static Logger getLogger() {
        return LOGGER_CONTEXT.get();
    }
}
