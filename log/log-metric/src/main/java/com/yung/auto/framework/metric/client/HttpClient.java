package com.yung.auto.framework.metric.client;

import com.yung.auto.framework.metric.model.MetricBuilder;
import com.yung.auto.framework.metric.model.response.Response;

import java.io.IOException;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface HttpClient extends Client {
    Response pushMetrics(MetricBuilder builder) throws IOException;
}
