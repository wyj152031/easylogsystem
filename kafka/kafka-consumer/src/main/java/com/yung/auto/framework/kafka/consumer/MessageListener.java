package com.yung.auto.framework.kafka.consumer;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface MessageListener {

    /**
     * 消费消息
     *
     * @param event
     */
    void onMessage(MessageEvent event);
}
