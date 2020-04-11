package com.yung.auto.framework.common.constants;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface ServiceConstants {
    /**
     * tag名称
     */
    String APP_ID = "appId";
    String SERVICE_TYPE = "serviceType";
    String RESTFUL_SERVICE = "restfulService";
    String DUBBO_SERVICE_PROVIDER = "dubboServiceProvider";
    String DUBBO_SERVICE_CONSUMER = "dubboServiceConsumer";

    String SERVICE_CODE = "serviceCode";
    String DUBBO_SERVICE = "dubboService";

    String PATH = "path";

    String INTERVAL_TIME = "intervalTime";
    String HOST_IP = "hostIp";
    String CLIENT_IP = "clientIp";
    String USER_ID = "userId";
    String QQT_ID = "qqtId";
    String DUBBO_SERVICE_HOST = "dubboServiceHost";

    String THREAD_ID = "threadId";
    String THREAD_NAME = "threadName";

    /**
     * 日志处理的key
     */
    String LOG_REQ_KEY = "req";
    String LOG_RESP_KEy = "resp";
    String LOG_REQUEST_TIME_KEY = "requestTime";
    String LOG_RESPONSE_TIME_KEY = "responseTime";
}
