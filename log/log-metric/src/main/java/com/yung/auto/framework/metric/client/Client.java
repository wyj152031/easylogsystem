package com.yung.auto.framework.metric.client;

import com.yung.auto.framework.metric.model.MetricBuilder;
import com.yung.auto.framework.metric.model.response.Response;

import java.io.IOException;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface Client {

    String PUT_POST_API = "/api/put";

    String QUERY_POST_API = "/api/query";

    /**
     * Sends metrics from the builder to the KairosDB server.
     *
     * @param builder metrics builder
     * @return response from the server
     * @throws IOException problem occurred sending to the server
     */
    Response pushMetrics(MetricBuilder builder) throws IOException;

}
