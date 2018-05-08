package com.yung.auto.framework.cache.local.cacheinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
@Component
public class CacheInitCore implements ApplicationListener<ApplicationEvent>{

    @Autowired(required = false)
    private List<CacheInitService> cacheInitServices;
    private AtomicBoolean isStarted=new AtomicBoolean(false);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(!isStarted.get()&&cacheInitServices!=null){
            for(CacheInitService c:cacheInitServices){
                c.init();
            }
            isStarted.set(true);
        }
    }
}
