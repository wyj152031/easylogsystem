package com.yung.auto.framework.utility.log;

import com.yung.auto.framework.utility.entities.ILogger;
import com.yung.auto.framework.utility.entities.LoggerFactory;
import com.yung.auto.framework.utility.entities.LoggerSourceEnum;
import com.yung.auto.framework.utility.manage.LoggerContext;

import java.util.Random;

/**
 * Created by wangyujing on 2018/1/5.
 */
public class TestMain {

    public static void main(String[] args) {
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
        logger.addLogTag("Interval", String.valueOf(interval) + "ms");
        logger.addLogTag("ClientIPTwo", "clientip");
        logger.write();
    }
}
