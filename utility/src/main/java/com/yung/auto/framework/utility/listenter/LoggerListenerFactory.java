package com.yung.auto.framework.utility.listenter;

import com.yung.auto.framework.utility.clog.CLog;
import com.yung.auto.framework.utility.collection.CollectionUtils;
import com.yung.auto.framework.utility.entities.LoggerSourceEnum;
import com.yung.auto.framework.utility.formate.ILoggerFormat;
import com.yung.auto.framework.utility.formate.LogTextFormatImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class LoggerListenerFactory {

    private static volatile boolean initialed;
    private static ConcurrentHashMap<LoggerSourceEnum, List<ILoggerListener>> loggerSourceMap = null;
    private static ConcurrentHashMap<LoggerSourceEnum, List<ILoggerFormat>> loggerFormateMap = null;


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
            if (!CollectionUtils.hasElement(loggerFormateMap) || CollectionUtils.hasElement(loggerSourceMap)) {
                initSource();
                initFormate();
            }
        } catch (Exception ex) {
            CLog.error("loadConfig", ex);
        }
    }

    private static void initSource() {
        ConcurrentHashMap<LoggerSourceEnum, List<ILoggerListener>> loggerSourceEnumList = new ConcurrentHashMap<LoggerSourceEnum, List<ILoggerListener>>();
        List<ILoggerListener> list = new ArrayList<ILoggerListener>();
        list.add(new CLoggerListenerImpl());

        loggerSourceEnumList.put(LoggerSourceEnum.CLOG, list);
        loggerSourceMap = loggerSourceEnumList;
    }

    private static void initFormate() {

        ConcurrentHashMap<LoggerSourceEnum, List<ILoggerFormat>> loggerFormateList = new ConcurrentHashMap<LoggerSourceEnum, List<ILoggerFormat>>();
        List<ILoggerFormat> loggerFormats = new ArrayList<ILoggerFormat>();
        loggerFormats.add(new LogTextFormatImpl());

        loggerFormateList.put(LoggerSourceEnum.CLOG, loggerFormats);
        loggerFormateMap = loggerFormateList;
    }

    public static List<ILoggerListener> getLoggerListenerList(LoggerSourceEnum source) {
        if (CollectionUtils.hasElement(loggerSourceMap) && loggerSourceMap.containsKey(source)) {
            return loggerSourceMap.get(source);
        }
        return null;
    }

    public static List<ILoggerFormat> getLoggerFormateList(LoggerSourceEnum source) {
        if (CollectionUtils.hasElement(loggerFormateMap) && loggerFormateMap.containsKey(source)) {
            return loggerFormateMap.get(source);
        }
        return null;
    }
}
