package com.yung.auto.framework.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class CalculateUtils {
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    public static long add(long l1, long l2) {
        return l1 + l2;
    }

    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double d1, double d2) {
        return div(d1, d2, 12);
    }

    public static double div(double d1, double d2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }
}
