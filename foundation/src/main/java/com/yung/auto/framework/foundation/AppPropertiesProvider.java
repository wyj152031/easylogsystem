package com.yung.auto.framework.foundation;

import com.yung.auto.framework.foundation.spi.env.EnvType;
import com.yung.auto.framework.foundation.spi.host.IPUtil;
import com.yung.auto.framework.foundation.spi.provider.ApplicationProvider;

import static com.yung.auto.framework.foundation.constants.AppPropertiesConstants.*;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class AppPropertiesProvider {

    private static ApplicationProvider PROVIDER = Foundation.app();

    public static String getAppId() {
        return PROVIDER.getAppId();
    }

    public static boolean getLogSwitch() {
        return PROVIDER.getBooleanProperty(FRAMEWORK_LOG_SWITCH_KEY, true);
    }

    public static String getOpentsdbUrl() {
        // TODO 用于区分dev还是prod环境
        return PROVIDER.getProperty(OPENTSDB_URL_KEY, OPENTSDB_DEV_DEFAULT_URL);
    }

    public static boolean getMetricSwitch() {
        return PROVIDER.getBooleanProperty(FRAMEWORK_METRIC_SWITCH_KEY, true);
    }

    public static boolean getMetricLogEnableSwitch() {
        return PROVIDER.getBooleanProperty(FRAMEWORK_METRIC_LOG_ENABLE_SWITCH_KEY, false);
    }

    public static int getMetricBatchSize() {
        return PROVIDER.getIntProperty(FRAMEWORK_METRIC_BATCH_SIZE_KEY, FRAMEWORK_METRIC_BATCH_SIZE_VALUE);
    }

    public static int getAggregatePeriod() {
        return PROVIDER.getIntProperty(METRIC_AGGREGATE_DISPATCH_PERIOD_KEY, METRIC_AGGREGATE_DISPATCH_PERIOD_VALUE);
    }

    public static long getMetricCyclePeriod() {
        return PROVIDER.getLongProperty(METRIC_AGGREGATE_CYCLE_PERIOD_KEY, METRIC_AGGREGATE_CYCLE_PERIOD_VALUE);
    }

    public static int getDispatchQueueSize() {
        return PROVIDER.getIntProperty(METRIC_DISPATCH_QUEUE_SIZE_KEY, METRIC_DISPATCH_QUEUE_SIZE_VALUE);
    }

    public static String getHostIp() {
        return IPUtil.getIP();
    }


    public static EnvType getEnvType() {
        return PROVIDER.getEnvType();
    }
}
