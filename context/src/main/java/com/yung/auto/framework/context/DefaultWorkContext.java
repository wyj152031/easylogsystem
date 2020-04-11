package com.yung.auto.framework.context;

import com.yung.auto.framework.log.logger.Logger;
import com.yung.auto.framework.log.logger.LoggerFactory;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DefaultWorkContext implements WorkContext {

        private final String guid;
        private final Logger logger = LoggerFactory.createLogger(LoggerSourceEnum.APP_LOG);
        private String requestId;
        //    private final Object request;
        private Object response;
        private Map<String, Object> requestItem = new HashMap<>();

        DefaultWorkContext() {
            String uuid = UUID.randomUUID().toString();
            this.guid = uuid;
            this.requestId = uuid.replace("-", "").toLowerCase();
        }

        @Override
        public String getGuid() {
            return this.guid;
        }

        @Override
        public Logger getLogger() {
            return this.logger;
        }

        public Long getThreadId() {
            return Thread.currentThread().getId();
        }

        public String getRequestId() {
            return this.requestId;
        }

        public Object addItem(String key, Object item) {
            return requestItem.putIfAbsent(key, item);
        }

        public Object getItem(String key) {
            return requestItem.getOrDefault(key, null);
        }
    }
