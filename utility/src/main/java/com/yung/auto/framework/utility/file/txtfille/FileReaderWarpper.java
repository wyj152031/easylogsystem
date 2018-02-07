package com.yung.auto.framework.utility.file.txtfille;

import com.yung.auto.framework.utility.common.DateHelper;
import com.yung.auto.framework.utility.common.DefaultConfig;
import com.yung.auto.framework.utility.common.Strings;
import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by wangyujing on 2018/1/9.
 */
public class FileReaderWarpper {

    private static FileReaderWarpper instance;
    private static final int MAX_LINE_OFFSET = 150;
    private static final int MAX_TAG_SIZE = 6;

    public static FileReaderWarpper getInstance() {
        if (instance == null) {
            synchronized (FileReaderWarpper.class) {
                if (instance == null) {
                    instance = new FileReaderWarpper();
                }
            }
        }
        return instance;
    }

    public void fileTxtWriter(String fileName, String content, LogLevel logLevel, LogType logType, long createTime) {
        Date date = new Date();
        String dirFile = DateHelper.formateDataStr(date);
        FileReaderTxt.writeOrUpdateContent(dirFile, convertLogType(fileName, logLevel, logType, createTime), content, false);
    }

    public void fileTxtWriter(String fileName, Map<String, String> map, LogLevel logLevel, LogType logType) {
        Date date = new Date();
        long time = date.getTime();
        fileTxtWriter(fileName, logLevel, null, null, map, logType, time);
    }


    public void fileTxtWriter(String fileName, LogLevel logLevel, String message, Throwable throwable, Map<String, String> map, LogType logType, long createTime) {
        StringBuilder stringBuilder = new StringBuilder();
        if (throwable != null) {
            stringBuilder.append(throwable.toString());
            stringBuilder.append("\r\n");
        }
        if (!Strings.isNullOrEmpty(message)) {
            stringBuilder.append(message);
        }
        fileTxtWriter(fileName, stringBuilder.toString(), logLevel, logType, createTime);
    }

    /**
     * 对超过一定长度进行换行
     *
     * @param content
     * @return
     */
    private String changeLine(String content) {
        if (Strings.isNullOrEmpty(content)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int len = content.length();
        int pos = 0;
        while (pos < len) {
            int endPos = pos + MAX_LINE_OFFSET;
            if (endPos >= len) {
                endPos = len;
            }
            String sub = content.substring(pos, endPos);
            stringBuilder.append(sub);
            stringBuilder.append("\r\n");
            pos += MAX_LINE_OFFSET;
        }
        return stringBuilder.toString();
    }

    private String convertMapToContent(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (i >= MAX_TAG_SIZE) {
                stringBuilder.append("\r\n");
                i = 0;
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(":");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\t");
            i++;
        }
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }

    private String convertLogType(String name, LogLevel logLevel, LogType logType, long createTime) {
        if (name == null || name.length() == 0) {
            name = DefaultConfig.DEFAULT_FILE_NAME;
        }
        StringBuilder logTypeStr = new StringBuilder(name);
        if (logType != null && logLevel != null) {
            logTypeStr.append("-");
            logTypeStr.append(logType.toString());
            logTypeStr.append(logLevel.toString());
        }
        if (createTime > 0) {
            logTypeStr.append("-");
            logTypeStr.append(createTime);
        }

        return logTypeStr.toString();
    }


}
