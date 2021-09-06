package com.hh.core.basic.date;


import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by hh on 2020/3/4.
 * 时间工具类
 */
public class MyDateUtil {

    public static int getWeek(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(DateUtils.parseDate(date, "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

}
