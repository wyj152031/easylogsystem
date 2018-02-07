package com.yung.auto.framework.utility.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Created by wangyujing on 2018/1/5.
 */
public class LogWriter {

    private Log log = LogFactory.getLog(LogWriter.class);

    public void writeInfo(String info) {
        Date date = new Date();
        log.info(date.toString() + info);
//        System.out.println(info);
    }

    public void writeError(String info) {
        Date date = new Date();
        log.error(date.toString() + info);
//        System.out.println(info);
    }

    public void writeWarn(String info) {
        Date date = new Date();
        log.warn(date.toString() + info);
//        System.out.println(info);
    }


}
