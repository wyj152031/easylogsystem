package com.yung.auto.framework.utility;

import com.yung.auto.framework.utility.clog.CLog;
import com.yung.auto.framework.utility.common.PropertyFactory;
import com.yung.auto.framework.utility.entities.ILogger;
import com.yung.auto.framework.utility.entities.LoggerFactory;
import com.yung.auto.framework.utility.entities.LoggerSourceEnum;
import com.yung.auto.framework.utility.manage.LoggerContext;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class DemoTest {

    @Test
    public void test() {
        Long t1 = System.currentTimeMillis();
        ILogger logger = LoggerFactory.createLogger(LoggerSourceEnum.CLOG);
        logger.addLogTag("Url", "url");
        logger.setLogTitle("test");
        LoggerContext.setLogger(logger);

        logger.addLogTag("Status", "status");
        Random random = new Random();
        logger.addLogTag("SessionID", String.valueOf(random.nextInt(1000000)));
        logger.addLogContent("Header", "header");
        logger.addLogContent("Cookies", "cookies");
        logger.addLogContent("Request", "request");
        logger.addLogContent("HttpMethod", "httpmethod");
        logger.addLogContent("AllURL", "allurl");
        logger.addLogTag("TagType", "Service");
        long interval = System.currentTimeMillis() - t1;
        logger.addLogTag("Interval", String.valueOf(interval));
        logger.addLogTag("ClientIPTwo", "clientip");
        logger.write();
        Assert.assertTrue(true);
    }

    @Test
    public void test1() {
        Properties properties = new Properties();
        String name = "META-INF/clog.properties";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(name);
        try {
            properties.load(in);
        } catch (IOException e) {
            CLog.error("读取" + name + "文件出错", e);
        }
        Properties properties1 = PropertyFactory.INSTANCE.createProperties(name);
        Assert.assertTrue(true);
    }
}
