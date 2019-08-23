package com.cxy.customize.core.util;


import com.cxy.customize.core.date.DateField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @description 日期工具类
 * @author chengxinyu
 * @date 2019年7月28日下午2:42:24
 * @version 1.1.0
 */
public class DateUtil {

    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static final ThreadLocal<SimpleDateFormat> dateTimneFormat = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private DateUtil() {
        throw new IllegalStateException("不可实例化");
    }

    /**
     * 将Date转换成标砖日期格式
     *
     * @return
     */
    public static Date formatDate(Date date) throws ParseException {

        return dateFormat.get().parse(dateFormat.get().format(date));
    }

    /**
     * 得到今天
     *
     * @return
     */
    public static Date today() {

        return localDate2Date(LocalDate.now());
    }

    /**
     * 得到今天Str
     *
     * @return
     */
    public static String todayStr() {
        return dateFormat.get().format(today());
    }

    /**
     * 得到DateStr
     *
     * @return
     */
    public static String dateStr(Date date) {
        return dateFormat.get().format(date);
    }


    /**
     * 得到DateTimeStr
     *
     * @return
     */
    public static String dateTimeStr(Date date) {
        return dateTimneFormat.get().format(date);
    }



    /**
     * 得到n天前
     *
     * @return
     */
    public static Date getNDaysAgo(Date checkDate, int n) {
        return localDate2Date(date2LocalDate(checkDate).minusDays(n));
    }

    /**
     * 得到明天，即任务触发器真正的生效日期
     *
     * @return
     */
    public static Date getTommorow(Date checkDate) {
        return localDate2Date(date2LocalDate(checkDate).plusDays(1));
    }

    /**
     * 判断年份是不是闰年
     *
     * @param year 年份
     * @return
     */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * 判断日期是不是闰年 特殊日 如 {2020.2.29,2024.2.29}
     *
     * @param localDate
     * @return
     */
    public static boolean isSpecialDate(LocalDate localDate) {
        // 不是闰年的先退出
        if (!isLeapYear(localDate.getYear())) {
            return false;
        }
        // 不是2月的也退出
        if (2 != localDate.getMonthValue()) {
            return false;
        }
        // 非29号也退出

        return (29 == localDate.getDayOfMonth());

    }

    /**
     * Date -> localDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
       return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * Date -> localDate
     *
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDateTime(date).toLocalDate();
    }

    /**
     * localDate -> Date
     *
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获得以给日期为基准的偏移日期
     * @param date  基准日期
     * @param dateField
     * @param offset 偏移值  正前负后
     * @return
     */
    public static LocalDateTime offset(Date date, DateField dateField, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateField.getValue(),offset);
        //date->localDateTime
        return date2LocalDateTime(calendar.getTime());
    }


}
