package com.yung.auto.framework.log.logger.formate;

import com.alibaba.fastjson.JSON;
import com.yung.auto.framework.log.logger.entity.LoggerEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DefaultLoggerFormat implements LoggerFormat {

    @Override
    public void format(LoggerEvent logEvent) {
        String logText = logEvent.getLogText();
        if (StringUtils.isNotBlank(logText)) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(translate(logEvent));
        List<String> messages = logEvent.getMessages();
        setMessage(builder, messages);
        Map<String, String> properties = logEvent.getTags();
        setTag(builder, "Tags", properties);
        Map<String, String> contents = logEvent.getLogContent();
        setTag(builder, "StoredContent", contents);
        Map<String, Object> objectMap = logEvent.getLogObject();
        setObject(builder, objectMap);
        logEvent.setLogText(builder.toString());
    }

    private String translate(LoggerEvent loggerEvent) {
        Map<String, String> map = new HashMap<>();
        if (loggerEvent.getLoggerEnum() != null) {
            map.put("LoggerEnum", loggerEvent.getLoggerEnum().toString());
        }
        if (StringUtils.isNotBlank(loggerEvent.getTitle())) {
            map.put("Title", loggerEvent.getTitle());
        }
        if (StringUtils.isNotBlank(loggerEvent.getLogToken())) {
            map.put("LogToken", loggerEvent.getLogToken());
        }
        if (!CollectionUtils.isEmpty(loggerEvent.getIoIntervalMap())) {
            loggerEvent.getIoIntervalMap().forEach((k, v) -> {
                map.put(k, String.valueOf(v));
            });
        }
        return getMapStr(map);
    }

    private String getMapStr(Map<String, String> map) {
        if (CollectionUtils.isEmpty(map)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("\r\n");
        });
        return sb.toString();
    }

    private void setObject(StringBuilder builder, Map<String, Object> objectMap) {
        if (CollectionUtils.isEmpty(objectMap)) {
            return;
        }
        objectMap.forEach((k, v) -> {
            builder.append(k + ":").append(JSON.toJSONString(v)).append("\r\n");
        });
    }

    private void setTag(StringBuilder builder, String tag, Map<String, String> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return;
        }
        builder.append(String.format("----%s------------------------------------\r\n", tag));
        Iterator iterator = tags.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> keyValue = (Map.Entry<String, String>) iterator.next();
            String key = keyValue.getKey();
            if (StringUtils.isBlank(key)) {
                continue;
            }
            builder.append(key + "=");
            builder.append(keyValue.getValue() + "\r\n");
        }
    }

    private void setMessage(StringBuilder builder, List<String> messages) {
        int messagesCount = messages != null ? messages.size() : 0;
        int index = 0;
        for (int i = 0; i < messagesCount; i++) {
            String message = messages.get(i);
            if (StringUtils.isBlank(message)) {
                continue;
            }
            builder.append(String.format("Message-%d=", index + 1));
            builder.append(message + "\r\n");
            index++;
        }
    }
}
