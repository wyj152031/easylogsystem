package com.yung.auto.framework.cache.local.cacheaop;

import com.yung.auto.framework.cache.local.cacherefresh.CacheCacheableSignatureAdvice;
import com.yung.auto.framework.utility.agent.LogManager;
import com.yung.auto.framework.utility.trace.ILog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
@Component
@Aspect
public class CacheableAspect {
    ILog logger = LogManager.getLogger(CacheableAspect.class);

    @Autowired
    private CacheCacheableSignatureAdvice advice;

    @Around("@annotation(cacheable)")
    public Object cacheAround(ProceedingJoinPoint pj, Cacheable cacheable) {
        try {
            return advice.processing(pj);
        } catch (Throwable throwable) {
            logger.error(throwable.toString());
            return null;
        }
    }



}
