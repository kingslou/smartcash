package com.pos.posscan.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Samuel on 16/6/16.
 */
public class DateUtil {
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object object = new Object();

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateString;
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, "yyyyMMddhhmmss");
    }

    /**
     * Date转换成long。
     *
     * @param date 日期
     * @return long类型日期
     */
    public static long date2Long(Date date) {
        return date.getTime();
    }

    /**
     * String转换成long
     *
     * @param dateStr string日期
     * @param pattern 转换格式, 如"yyyy-MM-dd"
     * @return 日期
     */
    public static long string2Long(String dateStr, String pattern) {
        long time = 0;
        try {
            time = date2Long(getDateFormat(pattern).parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @return 当天0点毫秒数
     */
    public static long getTodayInMillis() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return today.getTimeInMillis();
    }

    /**
     * @return 昨天0点毫秒数
     */
    public static long getYesterdayInMillis() {
        return getTodayInMillis() - 24 * 60 * 60 * 1000;
    }

    /**
     * @return 明天0点毫秒数
     */
    public static long getTomorrowInMillis() {
        return getTodayInMillis() + 24 * 60 * 60 * 1000;
    }
}
