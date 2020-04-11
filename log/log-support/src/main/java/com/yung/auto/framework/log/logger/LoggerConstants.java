package com.yung.auto.framework.log.logger;

import java.util.Arrays;
import java.util.List;

import static com.yung.auto.framework.common.constants.ServiceConstants.*;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface LoggerConstants {
    String LOG_QUEUE_COUNT = "LogQueueCount";
    String LOG_TIME = "LogTime";

    int MAX_LOG_TEXT_LENGTH = 25000;
    int MAX_LOG_TAG_SIZE = 9;
    String[] REMOVE_VISIT_KEY_LIST = {
            "ClientIp", "Request", "RequestSize", "Head",
            "Response", "ResponseSize", "ErrorCode", "ErrorMsg",
            "Framework", "Interval", "IntervalScope", "oid",
            "RecordCount", "RequestTime", "ResponseTime", "Result",
            "ServiceName", "ServiceType", "RpcService", "RpcOperation",
            "SystemCode", "ThreadID", "ContentType", "ThreadId", "Language",
            "SourceId", "Operation", "RequestUrl", "ResponseSize",
            "auth"
    };

    List<String> REMOVE_TAGS_LIST = Arrays.asList(RESTFUL_SERVICE, DUBBO_SERVICE_PROVIDER, DUBBO_SERVICE_CONSUMER,
            SERVICE_TYPE, PATH, DUBBO_SERVICE_HOST);

    int REMOVE_VISIT_KEY_LENGTH = REMOVE_VISIT_KEY_LIST.length;
    String HT_LOG_INFO_WRITE_SWITCH = "htsc.log.writeInfo.switch";
    String HT_LOG_CONFIG = "htsc.tech.log.properties";

    String LOG_SPILT_KEY = "log_spilt_key";
    String LOG_SPILT_COUNT_INDEX = "log_spilt_count_index";

    String DEFAULT_LOGGER_NAME = "defaultLogger";
    String DEFAULT_TRACE_NAME = "defaultTraceName";
}
