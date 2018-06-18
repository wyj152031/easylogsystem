package com.yung.auto.framework.cache.redis.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by wangyj on 2018/6/17.
 */
@Component
public class RedisCacheMethod {

    @Cacheable(value = "Redis#30#50", key = "#key", cacheManager = "redisCacheManager")
    public Person getPersonObject(String key) {
        Person p = new Person(key, "WangYung" + key, 18);
        return p;
    }
}
