package com.yung.auto.framework.cache.local.config;

import com.yung.auto.framework.cache.local.GuavaCacheFactory;
import com.yung.auto.framework.cache.local.GuavaCacheManager;
import com.yung.auto.framework.cache.local.cacheinit.CacheInitServiceHolder;
import com.yung.auto.framework.cache.local.cacherefresh.CacheCacheableSignatureAdvice;
import com.yung.auto.framework.cache.local.cacherefresh.CacheSignatureRegistery;
import com.yung.auto.framework.cache.local.event.LavCacheEventSubject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
@EnableCaching(proxyTargetClass = true)
@EnableConfigurationProperties(LocalCacheConfigProperties.class)
@Configuration
public class LocalCacheConfiguration {

    private LocalCacheConfigProperties configProperties;

    public LocalCacheConfiguration(LocalCacheConfigProperties properties) {
        this.configProperties = properties;
    }

    private GuavaCacheFactory basicGuavaFactoryBean() {
        GuavaCacheFactory cacheFactory = new GuavaCacheFactory();
        cacheFactory.setName("BASIC");
        cacheFactory.setSpec(configProperties.toString());
        return cacheFactory;
    }

    @Bean
    @Primary
    public GuavaCacheManager guavaCacheManagerBean() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        Collection<Cache> caches = new HashSet<>();
        caches.addAll(basicGuavaFactoryBean().getCaches());
        List<GuavaCacheFactory> factories = customizeCacheFactory();
        for (GuavaCacheFactory factory : factories) {
            caches.addAll(factory.getCaches());
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    public List<GuavaCacheFactory> customizeCacheFactory() {
        List<GuavaCacheFactory> factories = new ArrayList<>();
        String config = configProperties.getCustomize();
        if (StringUtils.hasText(config)) {
            List<String> items = Arrays.asList(config.split(";"));
            for (String item : items) {
                List<String> var = Arrays.asList(item.split(":"));
                if (var.size() == 2) {
                    GuavaCacheFactory factory = new GuavaCacheFactory();
                    String[] names = var.get(0).split(",");
                    factory.setName(names);
                    factory.setSpec(var.get(1));
                    factories.add(factory);
                }
            }
        }
        return factories;
    }

    @Bean
    public CacheCacheableSignatureAdvice cacheCacheableSignatureAdvice() {
        return new CacheCacheableSignatureAdvice();
    }

    @Bean
    public CacheSignatureRegistery cacheSignatureRegistery() {
        return new CacheSignatureRegistery();
    }

    @Bean
    public CacheInitServiceHolder cacheInitServiceHolder() {
        return new CacheInitServiceHolder();
    }

    @Bean
    public LavCacheEventSubject lavCacheEventSubject() {
        return new LavCacheEventSubject();
    }

}
