package com.yung.auto.framework.utility.common;

import com.yung.auto.framework.utility.clog.CLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class PropertyFactory {
    private static final String SUFFIX_PROPETY = ".properties";
    private static final String DEFAULT_PROPERTY_NAME = "META-INF/clog.properties";
    public static final PropertyFactory INSTANCE = new PropertyFactory();

    public  Properties createProperties(String name) {
        if (Strings.isNullOrEmpty(name) || !name.endsWith(SUFFIX_PROPETY)) {
            name = DEFAULT_PROPERTY_NAME;
        }
        Properties properties = new Properties();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(name);
            properties.load(in);
        } catch (IOException e) {
            CLog.error("读取" + name + "文件出错", e);
        }
        return properties;
    }
}
