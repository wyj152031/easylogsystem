package com.yung.auto.framework.kafka.consumer;

import com.google.common.base.Stopwatch;
import com.yung.auto.framework.common.NamedThreadFactory;
import com.yung.auto.framework.log.logger.context.LoggerContext;
import com.yung.auto.framework.log.logger.enums.LoggerSourceEnum;
import com.yung.auto.framework.log.utils.LoggerUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.yung.auto.framework.kafka.consumer.TopicConstants.COMMA_SPILT_REGEX;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
@Component
public class KafkaConsumerInitializer implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerInitializer.class);

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 5,
            60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20),
            new NamedThreadFactory("auto-kafka-consumer"), new ThreadPoolExecutor.AbortPolicy());

    private static final ThreadPoolExecutor POLLER = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new NamedThreadFactory("auto-consumer-poller"));

    private KafkaConsumer<String, String> consumer;

    @Value("${kafka.consumer.initializer.switch:false}")
    private boolean isOpen;

    @Value("${kafka.topics:}")
    private String topics;
    @Value("${kafka.consumer.servers:}")
    private String serverIps;
    @Value("${group.id:}")
    private String groupId;
    @Value("${fetch.max.bytes:1024}")
    private String fetchMaxBytes;
    @Value("${enable.auto.commit:true}")
    private String autoCommit;
    @Value("${auto.offset.reset:latest}")
    private String autoOffsetReset;
    @Value("${consumer.poll.time.out:3000}")
    private long pollTimeOut;

    @Autowired
    private MessageListener messageListener;

    @Override
    public void afterPropertiesSet() {
        try {
            if (!isOpen || StringUtils.isEmpty(topics)) {
                return;
            }
            consumer = initConsumer();
            consumer.subscribe(Arrays.asList(topics.split(COMMA_SPILT_REGEX)));
            POLLER.execute(new MessageConsumerEvent());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private KafkaConsumer<String, String> initConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", serverIps);
        props.put("group.id", groupId);
        props.put("fetch.max.bytes", fetchMaxBytes);
        props.put("enable.auto.commit", autoCommit);
        props.put("auto.offset.reset", autoOffsetReset);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<String, String>(props);
    }

    private class OnMessageListener implements Runnable {

        private MessageEvent event;

        private OnMessageListener(MessageEvent event) {
            this.event = event;
        }

        @Override
        public void run() {
            Stopwatch stopWatch = Stopwatch.createStarted();
            Date requestTime = new Date();
            Date responseTime = new Date();
            com.yung.auto.framework.log.logger.Logger logger = com.yung.auto.framework.log.logger.LoggerFactory.createLogger(LoggerSourceEnum.APP_LOG);
            try {
                logger.addLogTag("threadId", Thread.currentThread().toString());
                logger.addLogTag("threadName", Thread.currentThread().getName());
                logger.addLogTag("Kafka-consumer-topic", event.getTopicName());
                logger.addLogObject("event", event);
                logger.addIOInterval("requestTime", requestTime.getTime());
                messageListener.onMessage(event);
                responseTime = new Date();
            } catch (Exception e) {
                responseTime = new Date();
                LoggerUtils.error("ConsumerMessageError:", e);
            } finally {
                logger.addIOInterval("responseTime", responseTime.getTime());
                stopWatch.elapsed();
                long interval = stopWatch.elapsed(TimeUnit.MILLISECONDS);
                logger.addLogTag("intervalTime", String.valueOf(interval));
                logger.write();
                LoggerContext.remove();
            }
        }
    }

    private class MessageConsumerEvent implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(pollTimeOut);
                    for (ConsumerRecord<String, String> record : records) {
                        if (StringUtils.isEmpty(record.topic())) {
                            return;
                        }

                        MessageEvent event = new MessageEvent();
                        event.setTopicName(record.topic());
                        event.setCreateTime(new Date(record.timestamp()));
                        event.setKey(record.key());
                        event.setValue(record.value());

                        EXECUTOR.execute(new OnMessageListener(event));
                    }
                }
            } catch (Exception e) {
                LOGGER.error("SubmitMessageEventError", e);
            }
        }
    }
}
