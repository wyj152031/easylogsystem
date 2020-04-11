package com.yung.auto.framework.foundation;

import com.yung.auto.framework.foundation.spi.NullProviderManager;
import com.yung.auto.framework.foundation.spi.ProviderManager;
import com.yung.auto.framework.foundation.spi.ServiceBootstrap;
import com.yung.auto.framework.foundation.spi.provider.ApplicationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public abstract class Foundation {

    private static final Logger LOGGER = LoggerFactory.getLogger(Foundation.class);
    private static Object lock = new Object();
    private static volatile ProviderManager manager;

    private static ProviderManager getManager() {
        if (manager == null) {
            Object var0 = lock;
            synchronized (lock) {
                if (manager == null) {
                    manager = (ProviderManager) ServiceBootstrap.loadFirst(ProviderManager.class);
                }
            }
        }

        return manager;
    }

    public static String getProperty(String name, String defaultValue) {
        try {
            return getManager().getProperty(name, defaultValue);
        } catch (Exception var3) {
            LOGGER.info(var3.toString());
            return defaultValue;
        }
    }

    public static ApplicationProvider app() {
        try {
            return (ApplicationProvider) getManager().provider(ApplicationProvider.class);
        } catch (Exception var1) {
            LOGGER.info(var1.toString());
            return NullProviderManager.provider;
        }
    }

    public static String asString() {
        try {
            return getManager().toString();
        } catch (Exception var1) {
            LOGGER.info(var1.toString());
            return NullProviderManager.provider.toString();
        }
    }

    static {
        getManager();
    }
}
