package com.yung.auto.framework.utility.common;

import java.util.Properties;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class ConfigMangaer {

    private static Properties clogProperties = null;
    private static ConfigMangaer instance = new ConfigMangaer();
    static {
        init();
    }

    private static void init() {
        clogProperties = PropertyFactory.INSTANCE.createProperties("META-INF/clog.properties");
    }

    public static Properties getClogProperties() {
        return clogProperties;
    }

    public static void setClogProperties(Properties clogProperties) {
        ConfigMangaer.clogProperties = clogProperties;
    }
}
