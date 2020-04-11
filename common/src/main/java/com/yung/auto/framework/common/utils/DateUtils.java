package com.yung.auto.framework.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.yung.auto.framework.common.constants.DateConstants.YYYY_MM_DD_HH_MM;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
        return dateFormat.format(date);
    }

    public static Date parse(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.toString());
        }
        return new Date();
    }

    public static Date parse(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.toString());
        }
        return new Date();
    }

    public static Calendar parse2Calendar(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = dateFormat.parse(dateStr);
            calendar.setTime(date);
        } catch (ParseException e) {
            LOGGER.error(e.toString());
        }
        return calendar;
    }
}
