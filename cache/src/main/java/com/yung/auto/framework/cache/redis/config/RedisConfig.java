package com.yung.auto.framework.cache.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yung.auto.framework.cache.redis.customiz.CustomizedRedisCacheManager;
import com.yung.auto.framework.utility.collection.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * Created by wangyj on 2018/6/17.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
//public class RedisConfig {
    @Autowired
    RedisConfigProperties redisConfigProperties;

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
                sb.append(method.getName());
                if (CollectionUtils.hasElement(params)) {
                    for (Object obj : params) {
                        if (obj != null) {
                            sb.append(obj.toString());
                        }
                    }
                }

                return sb.toString();
            }
        };
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲接连
        jedisPoolConfig.setMaxIdle(redisConfigProperties.getMaxIdle());
        //最小空闲连接
        jedisPoolConfig.setMinIdle(redisConfigProperties.getMinIdle());
        //连接池最大阻塞等待时间
        jedisPoolConfig.setMaxWaitMillis(redisConfigProperties.getMaxWait());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        //主机地址
        jedisConnectionFactory.setHostName(redisConfigProperties.getHost());
        //端口
        jedisConnectionFactory.setPort(redisConfigProperties.getPort());
        //密码
        jedisConnectionFactory.setPassword(redisConfigProperties.getPassword());
        //索引
        jedisConnectionFactory.setDatabase(redisConfigProperties.getDatabase());
        //超时时间
        jedisConnectionFactory.setTimeout(redisConfigProperties.getTimeOut());
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

//    @SuppressWarnings("rawtypes")
    @Bean
//    @Primary
    public CacheManager redisCacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        RedisCacheManager redisCacheManager = new CustomizedRedisCacheManager(redisTemplate);
        // 开启使用缓存名称最为key前缀
        redisCacheManager.setUsePrefix(true);
        //这里可以设置一个默认的过期时间 单位是秒
        redisCacheManager.setDefaultExpiration(600);

        //设置缓存过期时间
//        redisCacheManager.setDefaultExpiration(60);//秒
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
