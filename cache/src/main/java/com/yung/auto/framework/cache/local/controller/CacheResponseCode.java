package com.yung.auto.framework.cache.local.controller;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public interface CacheResponseCode {

    long SUCCESS = 100000;
    long FAILURE = 200000;
    long FAILURE_NOT_CONTAINS_KEY = 200100;
    long FAILURE_NOT_MATCH_HOSTIP = 200200;
    long FAILURE_NO_RESULT = 200300;
}
