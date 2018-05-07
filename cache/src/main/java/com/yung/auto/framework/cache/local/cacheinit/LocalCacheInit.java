package com.yung.auto.framework.cache.local.cacheinit;

import com.yung.auto.framework.cache.local.event.EventType;
import com.yung.auto.framework.cache.local.event.LavCacheEventBase;
import com.yung.auto.framework.cache.local.event.LavCacheEventSubject;
import com.yung.auto.framework.utility.agent.LogManager;
import com.yung.auto.framework.utility.trace.ILog;

import java.util.Map;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class LocalCacheInit {
    ILog logger = LogManager.getLogger(LocalCacheInit.class);

    public boolean initCache() {
        while (CacheInitServiceHolder.getContext() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Spring Applicationcontext initialiation failured.", e);
                return false;
            }
        }
        LavCacheEventSubject subject = CacheInitServiceHolder.getBean(LavCacheEventSubject.class);
        subject.notifyListeners(new LavCacheEventBase(EventType.UNSTART));

        Map<String, CacheInitService> cacheInitServices = CacheInitServiceHolder.getBeansOfType(CacheInitService.class);
        if (null != cacheInitServices) {
            subject.notifyListeners(new LavCacheEventBase(EventType.CACHE_LOADING));
            for (CacheInitService service : cacheInitServices.values()) {
                if (!service.init()) {
                    logger.error("Localcache Load Failure : " + service.getClass().getName());
                }
            }
        }
        subject.notifyListeners(new LavCacheEventBase(EventType.FINISHED));
        return true;
    }
}
