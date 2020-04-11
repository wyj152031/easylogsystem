package com.yung.auto.framework.foundation.spi;

import com.yung.auto.framework.foundation.spi.provider.Provider;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface ProviderManager {

    String getProperty(String var1, String var2);

    <T extends Provider> T provider(Class<T> var1);
}
