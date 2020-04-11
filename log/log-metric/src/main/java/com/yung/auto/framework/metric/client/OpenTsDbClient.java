package com.yung.auto.framework.metric.client;

import com.yung.auto.framework.metric.model.MetricBuilder;
import com.yung.auto.framework.metric.model.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

import static com.yung.auto.framework.common.constants.DateConstants.SECOND_PRE_MILL;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class OpenTsDbClient {
    private static Logger log = LoggerFactory.getLogger(OpenTsDbClient.class);
    /**
     * 取平均值的聚合器
     */
    public static String AGGREGATOR_AVG = "avg";
    /**
     * 取累加值的聚合器
     */
    public static String AGGREGATOR_SUM = "sum";
    private HttpClient httpClient;

    public OpenTsDbClient(String opentsdbUrl) {
        this.httpClient = new HttpClientImpl(opentsdbUrl);
    }

    /**
     * 写入数据
     *
     * @param metric    指标
     * @param timestamp 时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, Date timestamp, long value, Map tagMap) throws Exception {
        long timSecs = timestamp.getTime() / SECOND_PRE_MILL;
        return this.putData(metric, timSecs, value, tagMap);
    }

    /**
     * 写入数据
     *
     * @param metric    指标
     * @param timestamp 时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, Date timestamp, double value, Map tagMap) throws Exception {
        long timeSecs = timestamp.getTime() / SECOND_PRE_MILL;
        return this.putData(metric, timeSecs, value, tagMap);
    }

    /**
     * 写入数据
     *
     * @param metric    指标
     * @param timestamp 转化为秒的时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, long timestamp, long value, Map tagMap) throws Exception {
        MetricBuilder builder = MetricBuilder.getInstance();
        builder.addMetric(metric).setDataPoint(timestamp, value).addTags(tagMap);
        try {
            Response response = httpClient.pushMetrics(builder);
            return response.isSuccess();
        } catch (Exception e) {
            log.error("put data to opentsdb error: ", e);
            throw e;
        }
    }

    /**
     * 写入数据
     *
     * @param metric    指标
     * @param timestamp 转化为秒的时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, long timestamp, double value, Map tagMap) throws Exception {
        MetricBuilder builder = MetricBuilder.getInstance();
//        long tem = transferTime(timestamp);
        builder.addMetric(metric).setDataPoint(timestamp, value).addTags(tagMap);
        try {
            Response response = httpClient.pushMetrics(builder);
            return response.isSuccess();
        } catch (Exception e) {
            log.error("put data to opentsdb error: ", e);
            throw e;
        }
    }

    /**
     * 批量写入数据
     *
     * @param builder 指标
     * @return
     * @throws Exception
     */
    public boolean putDataBatch(MetricBuilder builder) throws Exception {
        try {
            Response response = httpClient.pushMetrics(builder);
            return response.isSuccess();
        } catch (Exception e) {
            log.error("put data to opentsdb error: ", e);
            throw e;
        }
    }
}
