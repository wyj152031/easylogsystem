package com.yung.auto.framework.log.listener;

import com.yung.auto.framework.log.logger.entity.LoggerEvent;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface LoggerListener {

    void write(LoggerEvent logEvent);
}
