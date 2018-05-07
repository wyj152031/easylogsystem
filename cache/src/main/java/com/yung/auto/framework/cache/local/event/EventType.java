package com.yung.auto.framework.cache.local.event;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public enum EventType {

    NONE(0), UNSTART(1), SPRING_INITIALIZATION(2), CACHE_LOADING(2), FINISHED(3);

    private int value;

    EventType(int v) {
        this.value = v;
    }

    public static EventType convert(int v) {
        switch (v) {
            case 1:
                return UNSTART;
            case 2:
                return SPRING_INITIALIZATION;
            case 3:
                return CACHE_LOADING;
            case 4:
                return FINISHED;
            default:
                return NONE;
        }
    }
}
