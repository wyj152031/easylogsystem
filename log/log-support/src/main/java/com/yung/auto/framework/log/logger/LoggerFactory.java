package com.yung.auto.framework.log.logger;

import com.yung.auto.framework.log.listener.LoggerListener;
import com.yung.auto.framework.log.listener.LoggerListenerFactory;
import com.yung.auto.framework.log.logger.context.LoggerContext;
import com.yung.auto.framework.log.logger.entity.DefaultLoggerEventImpl;
import com.yung.auto.framework.log.logger.entity.EmptyLoggerEventImpl;
import com.yung.auto.framework.log.logger.enums.LoggerEnum;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;
import com.yung.auto.framework.log.logger.formate.LoggerFormat;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class LoggerFactory {

    public static Logger createLogger(LoggerSourceEnum source) {
        LoggerListenerFactory.loadConfig();
        List<LoggerListener> loggerListeners = LoggerListenerFactory.getLoggerListenerList(source);
        List<LoggerFormat> loggerFormats = LoggerListenerFactory.getLoggerFormatList(source);
        if (!CollectionUtils.isEmpty(loggerListeners)) {
            Logger loggerContext = getLogger();
            if (loggerContext != null) {
                return loggerContext.cloneLog(source, LoggerEnum.APP);
            }
            UUID uuid = UUID.randomUUID();
            String logToken = uuid.toString();
            Logger logger = new DefaultLoggerEventImpl(logToken, source, LoggerEnum.APP, loggerListeners, loggerFormats);
            logger.addLogTag("Guid", logToken);
            LoggerContext.setLogger(logger);
            return logger;
        }
        return new EmptyLoggerEventImpl();
    }

    private static Logger getLogger() {
        return LoggerContext.getLogger();
    }
}
