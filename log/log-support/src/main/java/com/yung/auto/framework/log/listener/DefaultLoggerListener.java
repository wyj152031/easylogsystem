package com.yung.auto.framework.log.listener;

import com.yung.auto.framework.foundation.AppPropertiesProvider;
import com.yung.auto.framework.log.logger.LoggerConstants;
import com.yung.auto.framework.log.logger.enums.LoggerLevelEnum;
import com.yung.auto.framework.log.logger.entity.LoggerEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;


/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DefaultLoggerListener implements LoggerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoggerListener.class);

    public DefaultLoggerListener() {
    }

    @Override
    public void write(LoggerEvent logEvent) {
        Map<String, String> tags = logEvent.getTags();
        writeLog(logEvent, tags);
    }

    private void writeLog(LoggerEvent logEvent, Map<String, String> attrs) {
        String text = logEvent.getLogText();
        if (StringUtils.isNotBlank(text) && text.length() > LoggerConstants.MAX_LOG_TEXT_LENGTH) {
            UUID uuid = UUID.randomUUID();
            attrs.put(LoggerConstants.LOG_SPILT_KEY, uuid.toString());
            int preIndex = 0;
            int spiltCountIndex = 0;
            int len = text.length();
            while (preIndex < len) {
                int currentMaxIndex = preIndex + LoggerConstants.MAX_LOG_TEXT_LENGTH;
                if (currentMaxIndex >= len) {
                    currentMaxIndex = len;
                }
                String temp = text.substring(preIndex, currentMaxIndex);
                attrs.put(LoggerConstants.LOG_SPILT_COUNT_INDEX, String.valueOf(spiltCountIndex));
                writeLog(logEvent, temp, attrs);
                preIndex = currentMaxIndex;
                spiltCountIndex++;
            }
        } else {
            writeLog(logEvent, logEvent.getLogText(), attrs);
        }
    }

    private void writeLog(LoggerEvent logEvent, String text, Map<String, String> tags) {
        if (StringUtils.isBlank(text) || !AppPropertiesProvider.getLogSwitch()) {
            return;
        }
        LoggerLevelEnum loggerLevel = logEvent.getLoggerLevelEnum();
        if (loggerLevel == null) {
            loggerLevel = LoggerLevelEnum.INFO;
        }
        switch (loggerLevel) {
            case INFO:
                LOGGER.info(text, logEvent.getException());
                break;
            case WARN:
                LOGGER.warn(text, logEvent.getException());
                break;
            case ERROR:
                LOGGER.error(text, logEvent.getException());
                break;
            case DEBUG:
                LOGGER.debug(text, logEvent.getException());
                break;
        }
    }
}
