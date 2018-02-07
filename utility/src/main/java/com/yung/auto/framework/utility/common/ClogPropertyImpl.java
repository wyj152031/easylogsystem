package com.yung.auto.framework.utility.common;

import java.util.Properties;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class ClogPropertyImpl implements IProperty {

    private static Properties clogProperties = null;
    private static ClogPropertyImpl instance = new ClogPropertyImpl();

    static {
        clogProperties = ConfigMangaer.getClogProperties();
    }

    public static ClogPropertyImpl getInstance() {
        if(instance == null) {
            instance = new ClogPropertyImpl();
        }
        return instance;
    }

    @Override
    public  boolean getSwitchValue(String name, String defaultValue) {
        String value = clogProperties.getProperty(name, defaultValue);
        return Boolean.parseBoolean(value);
    }

    @Override
    public String getValue(String name, String defaultValue) {
        String value = clogProperties.getProperty(name, defaultValue);
        return value;
    }

    @Override
    public Integer getValueIntger(String name, String defaultValue) {
        String value = clogProperties.getProperty(name, defaultValue);
        return Integer.valueOf(value);
    }
}
