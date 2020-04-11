package com.yung.auto.framework.log.listener;

import com.yung.auto.framework.common.constants.ServiceType;
import com.yung.auto.framework.common.utils.CollectionUtils;
import com.yung.auto.framework.foundation.AppPropertiesProvider;
import com.yung.auto.framework.foundation.spi.env.EnvType;
import com.yung.auto.framework.log.executor.MetricThreadPool;
import com.yung.auto.framework.log.logger.entity.LoggerEvent;
import com.yung.auto.framework.metric.aggregate.MetricAggregateKey;
import com.yung.auto.framework.metric.aggregate.MetricEnum;
import com.yung.auto.framework.metric.utils.CountableKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yung.auto.framework.common.constants.ServiceConstants.*;
import static com.yung.auto.framework.metric.aggregate.MetricsConstants.AUTO_FRAMEWORK_REQUEST_METRIC;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class MetricLoggerListener implements LoggerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricLoggerListener.class);

    private static final List<String> COMMON_WHITE_TAGS = Arrays.asList(HOST_IP, APP_ID, SERVICE_TYPE);

    private static Map<ServiceType, List<String>> FILTER_WHITE_TAG_MAP = new HashMap<>(3);

    static {
        FILTER_WHITE_TAG_MAP.put(ServiceType.RESTFUL_SERVICE, Arrays.asList(SERVICE_CODE, RESTFUL_SERVICE));
        FILTER_WHITE_TAG_MAP.put(ServiceType.DUBBO_SERVICE_CONSUMER, Arrays.asList(SERVICE_CODE, DUBBO_SERVICE_CONSUMER));
        FILTER_WHITE_TAG_MAP.put(ServiceType.DUBBO_SERVICE_PROVIDER, Arrays.asList(SERVICE_CODE, DUBBO_SERVICE_PROVIDER));
    }

    @Override
    public void write(LoggerEvent logEvent) {
        if (!AppPropertiesProvider.getMetricSwitch() || EnvType.PROD == AppPropertiesProvider.getEnvType()) {
            return;
        }
        try {
            MetricAggregateKey metricKey = buildMetricKey(logEvent);
            String interval = logEvent.getTags().getOrDefault(INTERVAL_TIME, null);
            if (metricKey == null || interval == null) {
                return;
            }
            metricKey.setValue(Double.parseDouble(interval));
            MetricThreadPool.put(metricKey);
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        }
    }

    private MetricAggregateKey buildMetricKey(LoggerEvent logEvent) {
        if (!CollectionUtils.hasElement(logEvent.getTags())) {
            return null;
        }
        Map<String, String> tags = new HashMap<>();
        ServiceType serviceType = null;
        for (Map.Entry<String, String> entry : logEvent.getTags().entrySet()) {
            if (!COMMON_WHITE_TAGS.contains(entry.getKey())) {
                continue;
            }
            if (SERVICE_TYPE.equals(entry.getKey())) {
                serviceType = ServiceType.findByValue(entry.getValue());
            }
            tags.put(entry.getKey(), entry.getValue());
        }
        if (!CollectionUtils.hasElement(tags) || serviceType == null) {
            return null;
        }
        List<String> filterTag = FILTER_WHITE_TAG_MAP.get(serviceType);
        if (CollectionUtils.hasElement(filterTag)) {
            logEvent.getTags().forEach((k, v) -> {
                if (!filterTag.contains(k)) {
                    return;
                }
                tags.put(k, v);
            });
        }

        MetricAggregateKey aggregateKey = new MetricAggregateKey();
        aggregateKey.setTags(tags);
        aggregateKey.setMetricName(AUTO_FRAMEWORK_REQUEST_METRIC);
        aggregateKey.setPrimaryKey(CountableKeyUtils.generateKey(AUTO_FRAMEWORK_REQUEST_METRIC, tags));
        aggregateKey.setMetricEnum(MetricEnum.ALL);
        return aggregateKey;
    }
}
