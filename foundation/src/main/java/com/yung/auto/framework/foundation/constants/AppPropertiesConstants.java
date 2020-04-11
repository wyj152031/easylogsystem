package com.yung.auto.framework.foundation.constants;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface AppPropertiesConstants {

    String FRAMEWORK_APP_ID_KEY = "framework.app.id";
    String FRAMEWORK_LOG_SWITCH_KEY = "framework.log.switch";
    String FRAMEWORK_METRIC_SWITCH_KEY = "framework.metric.switch";
    String FRAMEWORK_METRIC_LOG_ENABLE_SWITCH_KEY = "framework.metric.log.enable.switch";
    String FRAMEWORK_METRIC_BATCH_SIZE_KEY = "framework.metric.batch.size";
    String METRIC_AGGREGATE_DISPATCH_PERIOD_KEY = "metric.aggregate.dispatch.period";
    String OPENTSDB_URL_KEY = "metric.opentsdb.url";
    String METRIC_AGGREGATE_CYCLE_PERIOD_KEY = "metric.aggregate.cycle.period";
    String METRIC_DISPATCH_QUEUE_SIZE_KEY = "metric.dispatch.queue.size";

    String FRAMEWORK_APP_ID_DEFAULT_VALUE = "100000000";
    int FRAMEWORK_METRIC_BATCH_SIZE_VALUE = 30;
    String OPENTSDB_DEV_DEFAULT_URL = "http://168.61.70.210:4242";
    int METRIC_AGGREGATE_DISPATCH_PERIOD_VALUE = 60;
    long METRIC_AGGREGATE_CYCLE_PERIOD_VALUE = 1000L;
    int METRIC_DISPATCH_QUEUE_SIZE_VALUE = 6000;
}
