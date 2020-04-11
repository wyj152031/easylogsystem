package com.yung.auto.framework.metric.utils;

import com.yung.auto.framework.common.utils.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class CountableKeyUtils {

    private static final String METRIC_NAME_SEPARATOR = "#";
    private static final String TAG_SEPARATOR = "@";
    private static final String BETWEEN_TAG_SEPARATOR = ";";
    private static final Pattern TAG_KV_PATTERN = Pattern.compile("@");
    private static final Pattern TAGS_PATTERN = Pattern.compile(";");

    public static String generateKey(String metricName, Map<String, String> tags) {
        if (StringUtils.isEmpty(metricName) || !CollectionUtils.hasElement(tags)) {
            return metricName;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(metricName).append(METRIC_NAME_SEPARATOR);
        tags.forEach((k, v) -> {
            sb.append(k).append(TAG_SEPARATOR).append(v).append(BETWEEN_TAG_SEPARATOR);
        });
        return sb.substring(0, sb.length() - 1);
    }

    public static Map<String, String> parseTag(String tagStr) {
        if (tagStr.contains(METRIC_NAME_SEPARATOR)) {
            int index = tagStr.indexOf(METRIC_NAME_SEPARATOR);
            tagStr = tagStr.substring(index);
        }
        Map<String, String> tagMap = new HashMap();
        String[] tags = TAGS_PATTERN.split(tagStr);
        String[] var3 = tags;
        int var4 = tags.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String tag = var3[var5];
            String[] tagStrs = TAG_KV_PATTERN.split(tag);
            if (tagStrs.length > 1) {
                String tagKey = tagStrs[0];
                String tagValue = tagStrs[1];
                tagMap.put(tagKey, tagValue);
            }
        }

        return tagMap;
    }
}
