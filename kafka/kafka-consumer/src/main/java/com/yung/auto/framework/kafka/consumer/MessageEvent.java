package com.yung.auto.framework.kafka.consumer;

import lombok.Data;

import java.util.Date;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
@Data
public class MessageEvent {

    /**
     * 主题
     */
    private String topicName;
    /**
     * 消息创建时间
     */
    private Date createTime;

    /**
     * 消息实体key
     */
    private String key;
    /**
     * 消息实体value
     */
    private String value;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
