package com.yung.auto.framework.utility.clog;

import com.yung.auto.framework.utility.trace.CLoggingTracer;

/**
 * @autor wangyujing
 * @date 2018/2/5.
 */
public class CLogFactory {

    private static CLoggingTracer cLoggingTracer;

    static {
        cLoggingTracer = new CLoggingTracer("yung.mobile");
    }

    public static CLoggingTracer createCLoggingTracer() {
        return cLoggingTracer;
    }
}
