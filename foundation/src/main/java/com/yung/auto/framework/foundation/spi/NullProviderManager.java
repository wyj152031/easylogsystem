package com.yung.auto.framework.foundation.spi;

import com.yung.auto.framework.foundation.spi.provider.NullProvider;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class NullProviderManager implements ProviderManager {

    public static final NullProvider provider = new NullProvider();

    public NullProviderManager() {
    }

    public String getProperty(String name, String defaultValue) {
        return defaultValue;
    }

    public NullProvider provider(Class clazz) {
        return provider;
    }

    public String toString() {
        return provider.toString();
    }
}
