package com.yung.auto.framework.utility.listenter;

import com.yung.auto.framework.foundation.Env;
import com.yung.auto.framework.utility.clog.CLogFactory;
import com.yung.auto.framework.utility.common.PropertyManager;
import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.entities.LogEntry;
import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;
import com.yung.auto.framework.utility.tags.LoggerStatics;
import com.yung.auto.framework.utility.trace.CLoggingTracer;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangyujing on 2018/1/26.
 */
public class CLoggerListenerImpl implements ILoggerListener {
    private CLoggingTracer cLoggingTracer;
    private static final int MAX_LOG_TEXT_LENGTH = 25000;
    private static final int MAX_LOG_TAG_SIZE = 9;
    private static final String[] REMOVE_VISIT_KEY_LIST = {
            "ClientIp", "Request", "RequestSize", "Head",
            "Response", "ResponseSize", "ErrorCode", "ErrorMsg",
            "Framework", "Interval", "IntervalScope", "oid",
            "RecordCount", "RequestTime", "ResponseTime", "Result",
            "ServiceName", "ServiceType", "SoaService", "SoaOperation",
            "SystemCode", "ThreadID", "ContentType", "ThreadId", "Language",
            "SourceId", "ClientIp", "Operation", "RequestUrl", "ResponseSize",
            "auth"
    };
    private static final int REMOVE_VISIT_KEY_LENGTH = REMOVE_VISIT_KEY_LIST.length;
    private static final String CLOG_INFO_WRITE_SWITCH = "Clog.WriteInfo.Switch";
    private static final String CLOG_CONFIG = "roc.fx.clog.properties";

    public CLoggerListenerImpl() {
        cLoggingTracer = CLogFactory.createCLoggingTracer();
    }

    @Override
    public void write(LogEntry logEntry) {
        Map<String, String> tags = logEntry.getLogTag();
        Map<String, String> newTags = new ConcurrentHashMap<>(logEntry.getLogTag());
        int size = tags.size();
        if (size > MAX_LOG_TAG_SIZE) {
            for (int i = 0; i < REMOVE_VISIT_KEY_LENGTH; i++) {
                if (tags.containsKey(REMOVE_VISIT_KEY_LIST[i])) {
                    newTags.remove(REMOVE_VISIT_KEY_LIST[i]);
                }
            }
        }
        preWriteClog(logEntry, newTags);
    }

    private void preWriteClog(LogEntry log, Map<String, String> addInfo) {
        String text = log.getLogText();
        if (Strings.isNullOrEmpty(text) && text.length() > MAX_LOG_TEXT_LENGTH) {
            UUID uuid = UUID.randomUUID();
            addInfo.put("spilt_key", uuid.toString());
            int spiltIndex = 0;
            int previousIndex = 0;
            boolean spiltLogText = true;
            int len = text.length();
            do {
                int takeLength = previousIndex + MAX_LOG_TEXT_LENGTH;
                if (takeLength >= len) {
                    takeLength = len;
                    spiltLogText = false;
                }
                addInfo.put("spilt_index", String.valueOf(spiltIndex));
                String newText = text.substring(previousIndex, takeLength);
                previousIndex = takeLength;
                writeCLog(log, newText, addInfo);
                spiltIndex++;
            } while (spiltLogText);
        } else {
            writeCLog(log, log.getLogText(), addInfo);
        }
    }

    private void writeCLog(LogEntry log, String text, Map<String, String> tags) {
        if (Strings.isNullOrEmpty(text)) {
            return;
        }
        LogLevel logLevel = log.getLoggerLevel();
        if (logLevel == null) {
            logLevel = LogLevel.INFO;
        }
        switch (logLevel) {
            case FATAL:
                realWriteClog(LogType.APP, LogLevel.FATAL, log.getTitle(), text, tags);
                break;
            case ERROR:
                realWriteClog(LogType.APP, LogLevel.ERROR, log.getTitle(), text, tags);
                break;
            case WARN:
                realWriteClog(LogType.APP, LogLevel.WARN, log.getTitle(), text, tags);
                break;
            case DEBUG:
                realWriteClog(LogType.APP, LogLevel.DEBUG, log.getTitle(), text, tags);
                break;
            default:
                realWriteClog(LogType.APP, LogLevel.INFO, log.getTitle(), text, tags);
                break;
        }
    }

    private void realWriteClog(LogType type, LogLevel level, String title, String message, Map<String, String> attrs) {
        Env env = LoggerStatics.getAppEnv();
        if (env.isPRO() && level == LogLevel.INFO && !PropertyManager.getSwitch()) {
            return;
        }

        cLoggingTracer.log(type, level, title, message, attrs);
    }
}
