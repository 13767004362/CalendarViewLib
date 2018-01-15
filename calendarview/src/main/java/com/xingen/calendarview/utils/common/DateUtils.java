package com.xingen.calendarview.utils.common;

import java.util.Calendar;

/**
 * Created by ${xinGen} on 2018/1/9.
 */

public class DateUtils {

    /**
     * 获取今天的日期
     * @return
     */
    public static String getTodayDate(){
        Calendar calendar = Calendar.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.YEAR));
        stringBuilder.append("-");
        stringBuilder.append(calendar.get(Calendar.MONTH) + 1);
        stringBuilder.append("-");
        stringBuilder.append(calendar.get(Calendar.DAY_OF_MONTH));
        return stringBuilder.toString();
    }
}
