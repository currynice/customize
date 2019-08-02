package com.cxy.customize.core.util;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    private  DateUtil() {
    }

    /**
     *  得到明天，即任务触发器真正的生效日期 todo 修改
     * @return
     */
    public static Date getTommorow(Date checkDate){
        return localDate2Date(date2LocalDate(checkDate).plusDays(1));
    }

    /**
     * 判断年份是不是闰年
     * @param year 年份
     * @return
     */
    public static boolean isLeapYear(int year){
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * 判断日期是不是闰年 特殊日  如 2020.2.29
     * @param  localDate
     * @return
     */
    public static boolean isSpecialDate(LocalDate localDate){
        //不是闰年的先退出
      if(!isLeapYear(localDate.getYear())){
          return false;
      }
      //不是2月的也退出
        if(2!=localDate.getMonthValue()){
            return false;
        }
        //非29号也退出
        if(29!=localDate.getDayOfMonth()){
            return false;
        }
        return true;
    }


    /**
     *  Date -> localDate
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     *  localDate -> Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date( LocalDate localDate){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }



}
