package com.yung.auto.framework.foundation.spi.env;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public enum  EnvType {

    DEV("dev"),
    SIT("sit"),
    UAT("uat"),
    PROD("prod");

    String value;

    EnvType(String value) {
        this.value = value;
    }

    public static EnvType findByValue(String value) {
        for (EnvType item : EnvType.values()) {
            if (value.equalsIgnoreCase(item.value)) {
                return item;
            }
        }
        return EnvType.DEV;
    }

    public String valueOf() {
        return this.value;
    }
}
