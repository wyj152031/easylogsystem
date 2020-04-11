package com.yung.auto.framework.foundation.spi.provider;

import com.yung.auto.framework.foundation.spi.env.EnvType;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface ApplicationProvider extends Provider {
    String getAppId();

    EnvType getEnvType();

    void setEnvType(EnvType value);
}
