package com.yung.auto.framework.utility.clog;

import com.yung.auto.framework.utility.trace.ILog;
import com.yung.auto.framework.utility.agent.LogManager;
import com.yung.auto.framework.utility.common.Strings;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class CLogManager {

    private static ILog clog = LogManager.getLogger(CLogManager.class);
    private static final int CAPACITIES_TAGS_MIN = 3;

    private static final String TAG_PROCESS_ID = "ProcessID";

    private static String CurrentProcessID = "";

    static {
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        String pid = jvmName.split("@")[0];
        CLogManager.CurrentProcessID = pid;
    }

    public static void info(String title, String message, String remark, Map<String, String> tags) {
        Map<String, String> map = createMapInfo(title, remark, tags);
        clog.info(title, message, map);
    }

    public static void info(String title, String message) {
        info(title, message, "", null);
    }

    private static Map<String, String> createMapInfo(String title, String remark, Map<String, String> tags) {
        int capacities = CAPACITIES_TAGS_MIN + (tags != null && tags.size() > 0 ? tags.size() : 0);
        Map<String,String> newHashMap = new HashMap<String, String>(capacities);

        if(!Strings.isNullOrEmpty(title)) {
            newHashMap.put("title",title);
        }
        if(!Strings.isNullOrEmpty(remark)) {
            newHashMap.put("remark",remark);
        }

        if(!newHashMap.containsKey(TAG_PROCESS_ID)) {
            newHashMap.put(TAG_PROCESS_ID,CLogManager.CurrentProcessID);
        }

        if(tags != null &&tags.size() > 0) {
            for(Map.Entry<String,String> item : tags.entrySet()) {
                newHashMap.put(item.getKey(),item.getValue());
            }
        }
        return newHashMap;
    }
}
