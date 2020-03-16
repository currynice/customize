package com.cxy.customize.core.util;


import com.cxy.customize.core.date.DateField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
        //秒数
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
       return LocalDateTime.ofInstant(instant, zone);
    }

    public static Instant getInstant(Date date){
        return date.toInstant();
    }

    //获取秒
    public static Long getSecond(Date date){
        return getInstant(date).getEpochSecond();
    }

    //获取毫秒
    public static Long getMilli(Date date){
        return getInstant(date).toEpochMilli();
    }

    //获取纳秒
    public static int getNano(Date date){
        return getInstant(date).getNano();
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

    //时间段构造
    public static Duration buildDuration(LocalDateTime from,LocalDateTime to){
        return Duration.between(from,to);
    }

    //时间段
    public static void testDurationInfo(){
        LocalDateTime from = LocalDateTime.of(2017, Month.JANUARY, 1, 00, 0, 0);    // 2017-01-01 00:00:00
        LocalDateTime to = LocalDateTime.of(2019, Month.SEPTEMBER, 12, 14, 28, 0);     // 2019-09-15 14:28:00
        Duration duration = buildDuration(from, to);     // 表示从 from 到 to 这段时间
        long days = duration.toDays();              // 这段时间的总天数
        long hours = duration.toHours();            // 这段时间的小时数
        long minutes = duration.toMinutes();        // 这段时间的分钟数
        long seconds = duration.getSeconds();       // 这段时间的秒数
        long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
        long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数
    }


    /**
     * isValid - dd/MM/yyyy with 20130925 = false
     * isValid - dd/MM/yyyy with 25/09/2013 = true
     * isValid - dd/MM/yyyy with 25/09/2013 12:13:50 = false
     * isValid - yyyy-MM-dd with 2017-18--15 = false
     * @param format
     * @param value
     * @param locale
     * @return
     */
    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    // Debugging purposes
                    //e2.printStackTrace();
                }
            }
        }

        return false;
    }




}

