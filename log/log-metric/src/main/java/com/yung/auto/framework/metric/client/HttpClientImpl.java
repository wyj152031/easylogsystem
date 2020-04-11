package com.yung.auto.framework.metric.client;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yung.auto.framework.foundation.AppPropertiesProvider;
import com.yung.auto.framework.metric.model.MetricBuilder;
import com.yung.auto.framework.metric.model.response.ErrorDetail;
import com.yung.auto.framework.metric.model.response.Response;
import com.yung.auto.framework.metric.model.response.SimpleHttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class HttpClientImpl implements HttpClient {


    private static Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);

    private String serviceUrl;

    private Gson mapper;

    private PoolingHttpClient httpClient = new PoolingHttpClient();

    public HttpClientImpl(String serviceUrl) {
        this.serviceUrl = serviceUrl;

        GsonBuilder builder = new GsonBuilder();
        mapper = builder.create();
    }

    @Override
    public Response pushMetrics(MetricBuilder builder) throws IOException {
        checkNotNull(builder);
        String data = builder.build();
        if (AppPropertiesProvider.getMetricLogEnableSwitch()) {
            logger.info("writeRequest:" + data);
        }
        SimpleHttpResponse response = httpClient.doPost(buildUrl(serviceUrl, PUT_POST_API), data);
        if (AppPropertiesProvider.getMetricLogEnableSwitch()) {
            logger.info("response:" + JSONObject.toJSONString(response));
        }

        return getResponse(response);
    }


    private String buildUrl(String serviceUrl, String postApiEndPoint) {
        String url = serviceUrl + postApiEndPoint + "?details";
        return url;
    }

    private Response getResponse(SimpleHttpResponse httpResponse) {
        Response response = new Response(httpResponse.getStatusCode());
        String content = httpResponse.getContent();
        if (StringUtils.isNotEmpty(content)) {
            if (response.isSuccess()) {
                ErrorDetail errorDetail = mapper.fromJson(content,
                        ErrorDetail.class);
                response.setErrorDetail(errorDetail);
            } else {
                logger.error("requestFailed!" + JSONObject.toJSONString(httpResponse));
            }
        }
        return response;
    }
}
