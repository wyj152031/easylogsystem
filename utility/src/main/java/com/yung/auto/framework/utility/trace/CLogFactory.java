package com.yung.auto.framework.utility.trace;

import com.yung.auto.framework.utility.trace.CLoggingTracer;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class CLogFactory {

    private static CLoggingTracer cLoggingTracer;

    static {
        cLoggingTracer = new CLoggingTracer("fc.mobile");
    }

    public static CLoggingTracer createCLoggingLogger() {
        return cLoggingTracer;
    }
}
