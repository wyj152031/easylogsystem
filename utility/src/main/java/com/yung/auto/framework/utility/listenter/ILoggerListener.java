package com.yung.auto.framework.utility.listenter;

import com.yung.auto.framework.utility.entities.LogEntry;

/**
 * Created by wangyujing on 2018/1/26.
 */
public interface ILoggerListener {

    void  write(LogEntry logEntry);
}
