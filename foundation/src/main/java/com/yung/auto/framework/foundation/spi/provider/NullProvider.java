package com.yung.auto.framework.foundation.spi.provider;

import com.yung.auto.framework.foundation.spi.env.EnvType;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class NullProvider extends AbstractProvider implements ApplicationProvider {

    @Override
    public Class<? extends Provider> getType() {
        return null;
    }

    @Override
    public String getProperty(String var1, String var2) {
        return null;
    }

    @Override
    public String getAppId() {
        return null;
    }

    @Override
    public void initialize() {

    }

    @Override
    public EnvType getEnvType() {
        return null;
    }

    @Override
    public void setEnvType(EnvType value) {

    }
}
