package com.yung.auto.framework.utility.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangyujing on 2018/1/9.
 */
public class DateHelper {

    public static String formateDataStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateStyle.STYLE_3);
        return simpleDateFormat.format(date);
    }
}
