package com.yung.auto.framework.foundation.spi;

import com.yung.auto.framework.foundation.spi.provider.DefaultApplicationProvider;
import com.yung.auto.framework.foundation.spi.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DefaultProviderManager implements ProviderManager {
    private static final Logger logger = LoggerFactory.getLogger(DefaultProviderManager.class);
    private Map<Class<? extends Provider>, Provider> providers = new LinkedHashMap();

    public DefaultProviderManager() {
        Provider applicationProvider = new DefaultApplicationProvider();
        applicationProvider.initialize();
        this.register(applicationProvider);
    }

    private void register(Provider provider) {
        this.providers.put(provider.getType(), provider);
    }

    @Override
    public <T extends Provider> T provider(Class<T> clazz) {
        Provider provider = (Provider)this.providers.get(clazz);
        if(provider != null) {
            return (T) provider;
        } else {
            logger.info(String.format("No provider [%s] found in DefaultProviderManager, please make sure it is " +
                    "registered in DefaultProviderManager ", new Object[]{clazz.getName()}), new String[0]);
            return (T) NullProviderManager.provider;
        }
    }

    @Override
    public String getProperty(String name, String defaultValue) {
        Iterator i$ = this.providers.values().iterator();

        String value;
        do {
            if(!i$.hasNext()) {
                return defaultValue;
            }

            Provider provider = (Provider)i$.next();
            value = provider.getProperty(name, (String)null);
        } while(value == null);

        return value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(512);
        if(null != this.providers) {
            Iterator i$ = this.providers.entrySet().iterator();

            while(i$.hasNext()) {
                Map.Entry<Class<? extends Provider>, Provider> entry = (Map.Entry)i$.next();
                sb.append(entry.getValue()).append("\n");
            }
        }

        sb.append("(DefaultProviderManager)").append("\n");
        return sb.toString();
    }
}
