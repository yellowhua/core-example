/**
 * Copyright:Copyright(c)2012-2013
 * Company:易联众信息技术股份有限公司
 *
 * @version 1.0
 */
package com.hh.core.business.lyrlzyw.ca.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @description: 格式化日期，转化为字符串格式
 * @date: 2012-10-23 下午04:23:07
 * @author: wangqm
 */
public class DateUtil {
    /**
     * @param format 指定字符串格式
     * @param date   日期
     * @return String
     * @description: 根据指定的格式化串来格式化日期
     * @date: 2012-10-23 下午04:36:35
     * @author： wangqm
     */
    public static String dateToString(String format, Date date) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param date 日期
     * @return String
     * @description: 将如期格式化为8位的字符串形式，如：20110415
     */
    public static String dateToString8(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * @param date 日期
     * @return String
     * @description: 将如期格式化为14位的字符串形式，如：20110415101112
     */
    public static String dateToString14(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * @param date yyyyMMdd格式的字符串日期
     * @return Date
     * @description: 将一个yyyyMMdd格式的字符串转行为日期
     */
    public static Date string8ToDate(String date) {
        if (date == null) {
            return null;
        }
        if (date.length() == 14) {
            return string14ToDate(date);
        }
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * @param date 字符串日期(yyyyMMddHHmmss格式)
     * @return Date
     * @description: 将一个yyyyMMddHHmmss格式的字符串转行为日期
     */
    public static Date string14ToDate(String date) {
        if (date == null) {
            return null;
        }
        if (date.length() == 8) {
            return string8ToDate(date);
        }
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        int house = Integer.parseInt(date.substring(8, 10));
        int minute = Integer.parseInt(date.substring(10, 12));
        int second = Integer.parseInt(date.substring(12));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, house);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几年后的时间
     *
     * @param d
     * @return
     */
    public static Date getYearAfter(Date d, int year) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + year);
        return now.getTime();
    }


    /**
     * yyyyMMdd
     */
    public static final String curPlainDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 24小时制，HHmmss
     */
    public static final String cur24HrTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String curFullTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * yyyyMMddHHmmss
     */
    public static final String curOrderTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getPlainDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String get24HrTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(date);
    }

    public static String getFullTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getPlainFullTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String getCurrentTime_yyyyMMddhhss() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime_str = sdf.format(nowTime);
        return nowTime_str;
    }

    /**
     * 取5位随机数
     *
     * @return
     */
    public static String get5Random() {
        int a = (int) (Math.random() * 90000 + 10000);
        return Integer.toString(a);
    }

    public static String getCodeIDRandom() {
        String s = get5Random() + System.currentTimeMillis();
        return s;
    }

    /**
     * 清除日期的时间部分，即变成：00:00:00.000
     *
     * @param date 日期
     * @return 日期
     */
    public static Date start(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 清除日期的时间部分，即变成：23:59:59.999
     *
     * @param date 日期
     * @return 日期
     */
    public static Date end(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }




    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    public static String getCurrentDate_String(String s) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return getDate(date, s);
    }

    public static String getDate(Date date, String s) {
        if (date == null) {
            return "";
        }
        Hashtable hashtable = new Hashtable();
        String s1 = "";
        String s2 = s.toLowerCase();
        if (s2.indexOf("yyyy") != -1) {
            hashtable.put(new Integer(s2.indexOf("yyyy")), "yyyy");
        } else if (s2.indexOf("yy") != -1) {
            hashtable.put(new Integer(s2.indexOf("yy")), "yy");
        }
        if (s2.indexOf("mm") != -1) {
            hashtable.put(new Integer(s2.indexOf("mm")), "MM");
        }
        if (s2.indexOf("dd") != -1) {
            hashtable.put(new Integer(s2.indexOf("dd")), "dd");
        }
        if (s2.indexOf("hh24") != -1) {
            hashtable.put(new Integer(s2.indexOf("hh24")), "HH");
        }
        if (s2.indexOf("mi") != -1) {
            hashtable.put(new Integer(s2.indexOf("mi")), "mm");
        }
        if (s2.indexOf("ss") != -1) {
            hashtable.put(new Integer(s2.indexOf("ss")), "ss");
        }
        for (int i = 0; s2.indexOf("-", i) != -1; i++) {
            i = s2.indexOf("-", i);
            hashtable.put(new Integer(i), "-");
        }

        for (int j = 0; s2.indexOf("/", j) != -1; j++) {
            j = s2.indexOf("/", j);
            hashtable.put(new Integer(j), "/");
        }

        for (int k = 0; s2.indexOf(" ", k) != -1; k++) {
            k = s2.indexOf(" ", k);
            hashtable.put(new Integer(k), " ");
        }

        for (int l = 0; s2.indexOf(":", l) != -1; l++) {
            l = s2.indexOf(":", l);
            hashtable.put(new Integer(l), ":");
        }

        if (s2.indexOf("年") != -1) {
            hashtable.put(new Integer(s2.indexOf("年")), "年");
        }
        if (s2.indexOf("月") != -1) {
            hashtable.put(new Integer(s2.indexOf("月")), "月");
        }
        if (s2.indexOf("日") != -1) {
            hashtable.put(new Integer(s2.indexOf("日")), "日");
        }
        if (s2.indexOf("时") != -1) {
            hashtable.put(new Integer(s2.indexOf("时")), "时");
        }
        if (s2.indexOf("分") != -1) {
            hashtable.put(new Integer(s2.indexOf("分")), "分");
        }
        if (s2.indexOf("秒") != -1) {
            hashtable.put(new Integer(s2.indexOf("秒")), "秒");
        }
        while (hashtable.size() != 0) {
            Enumeration enumeration = hashtable.keys();
            int j1 = 0;
            while (enumeration.hasMoreElements()) {
                int i1 = ((Integer) enumeration.nextElement()).intValue();
                if (i1 >= j1) {
                    j1 = i1;
                }
            }
            String s3 = (String) hashtable.get(new Integer(j1));
            hashtable.remove(new Integer(j1));
            s1 = s3 + s1;
        }
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s1, new DateFormatSymbols());
        return simpledateformat.format(date);
    }

    public static String getNextDate_String(String s) {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, +3);
        date = calendar.getTime();
        return getDate(date, s);
    }


    /**
     * 时间戳转为Date类型,只需要传入Object，转换出错时返回为空
     *
     * @param timeStamp
     * @return Date
     * @date  2018/7/29 上午10:14
    */
    public static Date timeStampToDate(Object timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (timeStamp == null){
            return date;
        }
        try {
            Long time = (Long) timeStamp;
            String d = format.format(time);
            date = format.parse(d);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }


}
