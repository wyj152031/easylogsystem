package com.yung.auto.framework.utility.entities;


import com.yung.auto.framework.utility.common.CollectionUtils;
import com.yung.auto.framework.utility.entities.DefaultLoggerImpl;
import com.yung.auto.framework.utility.entities.EmptyLoggerImpl;
import com.yung.auto.framework.utility.entities.ILogger;
import com.yung.auto.framework.utility.listenter.ILoggerListener;
import com.yung.auto.framework.utility.entities.LoggerSourceEnum;
import com.yung.auto.framework.utility.formate.ILoggerFormat;
import com.yung.auto.framework.utility.listenter.LoggerListenerFactory;
import com.yung.auto.framework.utility.manage.LoggerContext;

import java.util.List;
import java.util.UUID;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class LoggerFactory {

    public static ILogger createLogger(LoggerSourceEnum source) {
        LoggerListenerFactory.loadConfig();
        List<ILoggerListener> loggerListeners = LoggerListenerFactory.getLoggerListenerList(source);
        List<ILoggerFormat> loggerFormats = LoggerListenerFactory.getLoggerFormateList(source);
        if (CollectionUtils.hasElement(loggerListeners)) {
            ILogger loggerContext = getLogger();
            if (loggerContext != null) {
                return loggerContext.cloneLogger(source);
            }
            UUID uuid = UUID.randomUUID();
            String logToken = uuid.toString();
            ILogger logger = new DefaultLoggerImpl(logToken, source, loggerListeners, loggerFormats);
            logger.addLogTag("Guid", logToken);
            return logger;
        }
        return new EmptyLoggerImpl();
    }

    private static ILogger getLogger() {
        return LoggerContext.getLogger();
    }
}
