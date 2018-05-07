package com.yung.auto.framework.cache.local.cacherefresh;

import com.yung.auto.framework.cache.local.GuavaCacheManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class CacheCacheableSignatureAdvice {

    @Autowired
    private CacheSignatureRegistery registery;

    @Autowired
    private GuavaCacheManager cacheManager;

    private KeyGenerator keyGenerator;

    public CacheCacheableSignatureAdvice(){
        this.keyGenerator=new SimpleKeyGenerator();
    }

    /**
     * 缓存带有{@link Cacheable}注解的方法
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object processing(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature= (MethodSignature) pjp.getSignature();
        Object key=keyGenerator.generate(pjp.getTarget(),signature.getMethod(),pjp.getArgs());
        Cacheable anno=signature.getMethod().getAnnotation(Cacheable.class);
        String[] cacheNames=anno.cacheNames();
        registery.register(cacheNames,key,new CacheInvocationImpl(cacheNames,key,pjp.getTarget(),signature,pjp.getArgs()));
        return pjp.proceed();
    }


    /**
     *
     * @param cacheName
     * @param key
     * @return
     *        0 : SUCCESS
     *       -1 : Failure
     *       -2 : Target Method Signature invoke failure
     */
    public int update(String cacheName,String key){
        CacheInvocation invocation=registery.getCacheSignature(cacheName,key);
        Object value= null;
        try {
            value = invocation.invoke(key);
        } catch (Exception e) {
            return -2;
        }
        if(null!=value) {
            cacheManager.getCache(cacheName).put(key, value);
            return 0;
        }
        return -1;
    }
}
