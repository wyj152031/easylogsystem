package com.yung.auto.framework.foundation.spi.provider;

import com.yung.auto.framework.foundation.spi.parser.Parsers;

import java.util.Date;
import java.util.Locale;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public abstract class AbstractProvider implements ApplicationProvider {

    public AbstractProvider() {
    }

    public int getIntProperty(String name, int defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Integer.parseInt(value);
            }
        } catch (Throwable var4) {
            ;
        }

        return defaultValue;
    }

    public long getLongProperty(String name, long defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Long.parseLong(value);
            }
        } catch (Throwable var5) {
            ;
        }

        return defaultValue;
    }

    public short getShortProperty(String name, short defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Short.parseShort(value);
            }
        } catch (Throwable var4) {
            ;
        }

        return defaultValue;
    }

    public float getFloatProperty(String name, float defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Float.parseFloat(value);
            }
        } catch (Throwable var4) {
            ;
        }

        return defaultValue;
    }

    public double getDoubleProperty(String name, double defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Double.parseDouble(value);
            }
        } catch (Throwable var5) {
            ;
        }

        return defaultValue;
    }

    public byte getByteProperty(String name, byte defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Byte.parseByte(value);
            }
        } catch (Throwable var4) {
            ;
        }

        return defaultValue;
    }

    public boolean getBooleanProperty(String name, boolean defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Boolean.parseBoolean(value);
            }
        } catch (Throwable var4) {
            ;
        }

        return defaultValue;
    }

    public <T extends Enum<T>> T getEnumProperty(String name, Class<T> enumType, T defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Enum.valueOf(enumType, value);
            }
        } catch (Throwable var5) {
            ;
        }

        return defaultValue;
    }

    public Date getDateProperty(String name, Date defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Parsers.forDate().parse(value);
            }
        } catch (Throwable var4) {
            ;
        }

        return defaultValue;
    }

    public Date getDateProperty(String name, String format, Date defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Parsers.forDate().parse(value, format);
            }
        } catch (Throwable var5) {
            ;
        }

        return defaultValue;
    }

    public Date getDateProperty(String name, String format, Locale locale, Date defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Parsers.forDate().parse(value, format, locale);
            }
        } catch (Throwable var6) {
            ;
        }

        return defaultValue;
    }

    public long getDurationProperty(String name, long defaultValue) {
        try {
            String value = this.getProperty(name, (String)null);
            if(value != null) {
                return Parsers.forDuration().parseToMillis(value);
            }
        } catch (Throwable var5) {
            ;
        }

        return defaultValue;
    }
}
