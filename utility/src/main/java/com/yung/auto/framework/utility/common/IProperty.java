package com.yung.auto.framework.utility.common;

/**
 * @autor wangyujing
 * @date 2018/2/6.
 */
public interface IProperty {
     String getValue(String name, String defaultValue);

    boolean getSwitchValue(String name, String defaultValue);

    Integer getValueIntger(String name, String defaultValue);

}
