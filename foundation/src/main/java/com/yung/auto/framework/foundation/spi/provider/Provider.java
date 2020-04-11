package com.yung.auto.framework.foundation.spi.provider;

import java.util.Date;
import java.util.Locale;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface Provider {

    Class<? extends Provider> getType();

    String getProperty(String var1, String var2);

    int getIntProperty(String var1, int var2);

    long getLongProperty(String var1, long var2);

    short getShortProperty(String var1, short var2);

    float getFloatProperty(String var1, float var2);

    double getDoubleProperty(String var1, double var2);

    byte getByteProperty(String var1, byte var2);

    boolean getBooleanProperty(String var1, boolean var2);

    Date getDateProperty(String var1, Date var2);

    Date getDateProperty(String var1, String var2, Date var3);

    Date getDateProperty(String var1, String var2, Locale var3, Date var4);

    <T extends Enum<T>> T getEnumProperty(String var1, Class<T> var2, T var3);

    long getDurationProperty(String var1, long var2);

    void initialize();
}
