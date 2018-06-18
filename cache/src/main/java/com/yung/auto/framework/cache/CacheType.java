package com.yung.auto.framework.cache;


/**
 * Created by wangyj on 2018/6/17.
 */
public enum CacheType {
    REDIS("Redis"),
    LOCAL("Local");
    private String value ="default";

    CacheType(String value) {
        this.value = value;
    }

    public String valueOf() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }
}
