package com.yung.auto.framework.metric.aggregate.collector;

import com.yung.auto.framework.common.utils.CollectionUtils;
import com.yung.auto.framework.foundation.AppPropertiesProvider;
import com.yung.auto.framework.metric.aggregate.collector.generate.MetricGenerator;
import com.yung.auto.framework.metric.aggregate.collector.generate.MetricGeneratorFactory;
import com.yung.auto.framework.metric.aggregate.group.MetricAggregateGroup;
import com.yung.auto.framework.metric.aggregate.unit.MetricCountable;
import com.yung.auto.framework.metric.client.OpenTsDbClient;
import com.yung.auto.framework.metric.model.MetricBuilder;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class OpentsdbCollector implements Collector {

    private OpenTsDbClient openTsDbClient;
    private String opentsdbUrl = AppPropertiesProvider.getOpentsdbUrl();
    private int batchSendSize = AppPropertiesProvider.getMetricBatchSize();

    public OpentsdbCollector() {
        init();
    }

    private void init() {
        openTsDbClient = new OpenTsDbClient(opentsdbUrl);
    }


    @Override
    public void send(MetricAggregateGroup group) throws Exception {
        MetricBuilder builder = MetricBuilder.getInstance();
        MetricGenerator generator;
        for (MetricCountable countable : group.getMetricMap().values()) {
            generator = MetricGeneratorFactory.create(countable.getMetricEnum());
            generator.generateMetrics(builder, countable);
            if (!CollectionUtils.hasElement(builder.getMetrics())) {
                continue;
            }
            if (builder.getMetrics().size() >= batchSendSize) {
                openTsDbClient.putDataBatch(builder);
                builder = MetricBuilder.getInstance();
            }
        }
        if (!CollectionUtils.hasElement(builder.getMetrics())) {
            return;
        }
        openTsDbClient.putDataBatch(builder);
    }
}
