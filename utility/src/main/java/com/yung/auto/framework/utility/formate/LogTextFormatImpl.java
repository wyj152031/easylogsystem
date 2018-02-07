package com.yung.auto.framework.utility.formate;

import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.entities.LogEntry;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class LogTextFormatImpl implements ILoggerFormat {

    @Override
    public void format(LogEntry logEntry) {
        String logText = logEntry.getLogText();
        if (!Strings.isNullOrEmpty(logText)) {
            return;
        }
        StringBuffer builder = new StringBuffer();
        List<String> messages = logEntry.getMessages();
        setMessage(builder, messages);
        Map<String, String> properties = logEntry.getLogTag();
        setTag(builder, "Tags", properties);
        Map<String, String> contents = logEntry.getLogContent();
        setTag(builder, "StoredContent", contents);
        logEntry.setLogText(builder.toString());
    }

    private static void setTag(StringBuffer builder, String tag, Map<String, String> tags) {
        if (tags == null || tags.isEmpty()) {
            return;
        }
        builder.append(String.format("----%s------------------------------------\r\n", tag));
        Iterator iterator = tags.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> keyValue = (Map.Entry<String, String>) iterator.next();
            String key = keyValue.getKey();
            if (Strings.isNullOrEmpty(key)) {
                continue;
            }
            builder.append(key + ":");
            builder.append(keyValue.getValue() + "\r\n");
        }
    }

    private static void setMessage(StringBuffer builder, List<String> messages) {
        int messagesCount = messages != null ? messages.size() : 0;
        int index = 0;
        for (int i = 0; i < messagesCount; i++) {
            String message = messages.get(i);
            if (Strings.isNullOrEmpty(message)) {
                continue;
            }
            builder.append(String.format("Message %d:", index + 1));
            builder.append(message + "\r\n");
            index++;
        }
    }
}
