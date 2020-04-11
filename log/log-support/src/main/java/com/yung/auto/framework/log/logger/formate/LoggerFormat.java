package com.yung.auto.framework.log.logger.formate;

import com.yung.auto.framework.log.logger.entity.LoggerEvent;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface LoggerFormat {

    /**
     * 格式化logEvent内容
     *
     * @param logEvent
     */
    void format(LoggerEvent logEvent);
}
