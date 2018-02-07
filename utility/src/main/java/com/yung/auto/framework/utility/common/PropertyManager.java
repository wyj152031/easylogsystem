package com.yung.auto.framework.utility.common;

import com.yung.auto.framework.foundation.Env;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public class PropertyManager {

    private static IProperty property = null;

    static {
        property = new ClogPropertyImpl();
    }

    public static boolean getSwitch(){
        return property.getSwitchValue("clog.switch","true");
    }

    public static Env getEnv() {
        String env = property.getValue("clog.env","dev");
        switch(env.toUpperCase()) {
            case "DEV":
               return Env.DEV;
            case "FAT":
                return Env.FAT;
            case "PRO":
                return Env.PRO;
            default:
                return Env.DEV;
        }
    }

    public static String getAppId() {
        return property.getValue("clog.appid","10000");
    }

    public static Integer getLogMaxQueueCount() {
        return property.getValueIntger("clog.maxqueue.count","100");
    }

}
