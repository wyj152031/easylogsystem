package com.yung.auto.framework.cache.redis.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyj on 2018/6/17.
 */
@Repository
public class PersonDao {

    //存储简单字符串类型
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valops;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valopsStr;

    //存储字符串
    public void stringRedisTemplateDemo(){
        valopsStr.set("x", "y");
        valopsStr.set("expire","Time",10, TimeUnit.MINUTES);
    }
    //存储对象
    public void save(Person person){
        valops.set(person.getId(), person);
    }
    //获取字符串
    public String getString(){
        return valopsStr.get("x");
    }
    //获取对象
    public Object getPerson(){
        return  valops.get("1");
    }

    //获取对象
    public Object keys(){
        return  redisTemplate.keys("*");
    }

    public void setValops(ValueOperations<Object, Object> valops) {
        this.valops = valops;
    }

    public ValueOperations<String, String> getValopsStr() {
        return valopsStr;
    }

    public void setValopsStr(ValueOperations<String, String> valopsStr) {
        this.valopsStr = valopsStr;
    }
}
