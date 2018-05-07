package com.yung.auto.framework.cache.local.controller;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class CacheResponse {

    private long code;
    private Object result;
    private String msg;


    public CacheResponse(long code, String msg, Object value) {
        this.code = code;
        this.msg = msg;
        this.result = value;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
