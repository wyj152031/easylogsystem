package com.yung.auto.framework.utility.file;

import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;
import com.yung.auto.framework.utility.file.txtfille.FileReaderWarpper;

import java.util.Map;

/**
 * Created by wangyujing on 2018/1/26.
 */
public class LogFileTxtManager {

    private static LogFileTxtManager instance;

    public static LogFileTxtManager getInstance() {
        if(instance == null) {
            synchronized (LogFileTxtManager.class) {
                if(instance == null) {
                    instance = new LogFileTxtManager();
                }
            }
        }
        return instance;
    }

    public void writeLog(LogLevel logLevel, LogType logType, String title, String message, Throwable throwable, Map<String, String> attrs,
                         long createdTime){
        FileReaderWarpper.getInstance().fileTxtWriter(title,logLevel,message,throwable,attrs,logType,createdTime);
    }

}
