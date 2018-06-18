package com.yung.auto.framework.cache.redis.utils;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by wangyj on 2018/6/18.
 */
public class RedisTemplateUtils {
    private static RedisTemplate redisTemplate;

    public static RedisTemplate getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        if (redisTemplate == null) {
            synchronized (RedisTemplateUtils.class) {
                if (redisTemplate == null) {
                    redisTemplate = new RedisTemplate();
                    redisTemplate.setConnectionFactory(redisConnectionFactory);

                    JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
                    redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
                    redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

                    // 设置键（key）的序列化采用StringRedisSerializer。
                    redisTemplate.setKeySerializer(new StringRedisSerializer());
                    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
                    redisTemplate.afterPropertiesSet();
                }
            }

        }
        return redisTemplate;
    }
}
