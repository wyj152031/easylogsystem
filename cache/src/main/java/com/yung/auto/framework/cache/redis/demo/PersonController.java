package com.yung.auto.framework.cache.redis.demo;

import com.yung.auto.framework.cache.CacheType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangyj on 2018/6/17.
 */
@RestController
public class PersonController {

    @Resource
    private PersonDao personDao;

    @Autowired
    private RedisCacheMethod redisCacheMethod;

    @RequestMapping("/set")
    public void set(){
        Person p = new Person("1", "WangYung", 26);

        personDao.save(p);
        personDao.stringRedisTemplateDemo();
    }

    @RequestMapping("/getStr")
    public String getStr(){
        return personDao.getString();
    }

    @RequestMapping("redisCacheKey")
    public Person list(@RequestParam(value = "key", required = false) String key) {
        Person p = redisCacheMethod.getPersonObject(key);
        return p;
    }

    @RequestMapping("/getPerson")
    public Object getPerson(){
        return personDao.getPerson();
    }

    @RequestMapping("/keys")
    public Object keys(){
        return personDao.keys();
    }

}
