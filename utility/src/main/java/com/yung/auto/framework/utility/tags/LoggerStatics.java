package com.yung.auto.framework.utility.tags;

import com.yung.auto.framework.foundation.Env;
import com.yung.auto.framework.utility.common.PropertyManager;
import com.yung.auto.framework.utility.common.Strings;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class LoggerStatics {

    private static Env appEnv;
    private static String appId;
    private static String serverIp;
    private final static HashMap<String, String> WHITE_LOG_TAG = new HashMap<>();

    static {
        appId = PropertyManager.getAppId();
        appEnv = PropertyManager.getEnv();
        initWhiteProperties();
    }

    public static Env getAppEnv() {
        return appEnv;
    }

    public final static ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat;
    });

    public static String getAppid() {
        if (Strings.isNullOrEmpty(appId)) {
            appId = "10000";
        }
        return appId;
    }

    private static void initWhiteProperties() {
        WHITE_LOG_TAG.put("orderid", "1");
//        String key = AppProperties.getProperty("AutoSetAllLogTagNames");
        String key ="key";
        if (Strings.isNullOrEmpty(key)) {
            return;
        }
        String[] keys = key.split(",");
        for (int i = 0, len = keys.length; i < len; i++) {
            String tempKey = keys[i];
            if (Strings.isNullOrEmpty(tempKey)) {
                continue;
            }
            tempKey = tempKey.toLowerCase();
            if (!WHITE_LOG_TAG.containsKey(tempKey)) {
                WHITE_LOG_TAG.put(tempKey, "1");
            }
        }
    }

    public static boolean hasWhiteProperties(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return false;
        }
        return WHITE_LOG_TAG.containsKey(key);
    }

    public static String getServerIp() {
        if (Strings.isNullOrEmpty(serverIp)) {
            return "127.0.0.1";
        }
        return serverIp;
    }

    public static void getServerIp(String serverIp) {
        if (Strings.isNullOrEmpty(LoggerStatics.serverIp)) {
            LoggerStatics.serverIp = serverIp;
        }
    }
}
