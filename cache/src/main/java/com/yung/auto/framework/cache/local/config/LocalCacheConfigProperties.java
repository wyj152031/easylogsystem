package com.yung.auto.framework.cache.local.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
@ConfigurationProperties(prefix = "auto.fw.cache.guava")
public class LocalCacheConfigProperties {
    private String customize;
    private int maxSize = 1000;
    private String expireAfterAccess = "30d";
    private String expireAfterWrite = "30d";

    public String getCustomize() {
        return customize;
    }

    public void setCustomize(String customize) {
        this.customize = customize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getExpireAfterAccess() {
        return expireAfterAccess;
    }

    public void setExpireAfterAccess(String expireAfterAccess) {
        this.expireAfterAccess = expireAfterAccess;
    }

    public String getExpireAfterWrite() {
        return expireAfterWrite;
    }

    public void setExpireAfterWrite(String expireAfterWrite) {
        this.expireAfterWrite = expireAfterWrite;
    }

    @Override
    public String toString() {
        return String.format("maximumSize=%d,expireAfterAccess=%s,expireAfterWrite=%s", this.maxSize,
                this.expireAfterAccess, this.expireAfterWrite, customize);
    }

}
