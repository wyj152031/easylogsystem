package com.yung.auto.framework.common.constants;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public enum ServiceType {

    RESTFUL_SERVICE("restfulService"),
    DUBBO_SERVICE_PROVIDER("dubboServiceProvider"),
    DUBBO_SERVICE_CONSUMER("dubboServiceConsumer");

    private String value;

    ServiceType(String value) {
        this.value = value;
    }

    public static ServiceType findByValue(String value) {
        for (ServiceType item : ServiceType.values()) {
            if(value.equals(item.value)) {
                return item;
            }
        }
        return ServiceType.RESTFUL_SERVICE;
    }

    public String valueOf() {
        return this.value;
    }
}
