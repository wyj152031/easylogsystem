package com.yung.auto.framework.log.listener;

import com.yung.auto.framework.log.logger.formate.DefaultLoggerFormat;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;
import com.yung.auto.framework.log.logger.formate.LoggerFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class LoggerListenerFactory {
    private static volatile boolean initialed;
    private static ConcurrentHashMap<LoggerSourceEnum, List<LoggerListener>> loggerSourceMap = null;
    private static ConcurrentHashMap<LoggerSourceEnum, List<LoggerFormat>> loggerFormatMap = null;
    private static Logger LOGGER = LoggerFactory.getLogger(LoggerListenerFactory.class);

    static {
        init();
    }

    private synchronized static void init() {
        if (initialed) {
            return;
        }
        initialed = true;
        loadConfig();
    }

    public static void loadConfig() {
        try {
            if (!CollectionUtils.isEmpty(loggerFormatMap) || !CollectionUtils.isEmpty(loggerSourceMap)) {
                initSource();
                initFormat();
            }
        } catch (Exception ex) {
            LOGGER.error("loadConfig", ex);
        }
    }

    private static void initSource() {
        ConcurrentHashMap<LoggerSourceEnum, List<LoggerListener>> loggerSourceEnumList = new ConcurrentHashMap<LoggerSourceEnum, List<LoggerListener>>();
        List<LoggerListener> list = new ArrayList<LoggerListener>();
        list.add(new DefaultLoggerListener());
//        list.add(new MetricLoggerListener());

        loggerSourceEnumList.put(LoggerSourceEnum.APP_LOG, list);
        loggerSourceMap = loggerSourceEnumList;
    }

    private static void initFormat() {
        ConcurrentHashMap<LoggerSourceEnum, List<LoggerFormat>> loggerFormatList = new ConcurrentHashMap<LoggerSourceEnum, List<LoggerFormat>>();
        List<LoggerFormat> loggerFormats = new ArrayList<LoggerFormat>();
        loggerFormats.add(new DefaultLoggerFormat());

        loggerFormatList.put(LoggerSourceEnum.APP_LOG, loggerFormats);
        loggerFormatMap = loggerFormatList;
    }

    public static List<LoggerListener> getLoggerListenerList(LoggerSourceEnum source) {
        if (!CollectionUtils.isEmpty(loggerSourceMap) && loggerSourceMap.containsKey(source)) {
            return loggerSourceMap.get(source);
        }
        return null;
    }

    public static List<LoggerFormat> getLoggerFormatList(LoggerSourceEnum source) {
        if (!CollectionUtils.isEmpty(loggerFormatMap) && loggerFormatMap.containsKey(source)) {
            return loggerFormatMap.get(source);
        }
        return null;
    }
}
