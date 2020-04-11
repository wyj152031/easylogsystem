package com.yung.auto.framework.context;

import com.yung.auto.framework.log.logger.Logger;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface WorkContext {
    String getGuid();

    Logger getLogger();

    Long getThreadId();

    String getRequestId();
}
