package com.cxy.customize.core.date;



import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.util.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 * Description:   <br>
 * Date: 2020/6/4 17:34  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class TimeFormet {

    //返回时间 12:12:10
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");


    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    public static final String timeFormat(Date date) {
        LocalDateTime dateTime = DateUtil.date2LocalDateTime(date);
        return timeFormat(dateTime);
    }


    /**
     * 时间显示逻辑：
     * 0-1分钟 刚刚
     * 1-60分钟 x分钟前
     * 1-3小时 x小时前
     * 超过三小时：今天  ，昨天 ，前天 xx:xx
     * 最后 xxxx-xx-xx xx:xx
     */
    public static final String timeFormat(LocalDateTime  dateTime) {

        LocalDateTime now = LocalDateTime.now();


        if(dateTime.isAfter(now)){
            throw new UtilException("参数错误");
        }
        String timeStr = dateTime.format(TIME_FORMAT);

        long second = dateTime.until(now, ChronoUnit.SECONDS);

        int daysBetween = getDaysBetween(now,dateTime);

        long temp;
        if (second < 60) {
            return  "刚刚";
        }else if( (temp = second/60) < 60){
            return  temp + "分钟前";
        }else if( (temp = second/(60*60)) < 3){
            return  temp + "小时前";
        } else if(daysBetween == 0 ){
            return  "今天" + timeStr;
        } else if(daysBetween == 1){
            return  "昨天" + timeStr;
        } else if(daysBetween == 2){
            return  "前天" + timeStr;
        }
        return  dateTime.format(DATETIME_FORMAT);
    }



    /**
     * 获得同年的两个日期的天数差距
     * 2020-06-05 00:00:01 和 2020-06-04 23:59:59  -> return 1
     * 2020-06-05 00:00:00 和 2020-06-04 23:59:59  -> return 1
     * 2020-06-05 00:00:00 和 2020-06-03 23:59:59  -> return 2
     * @param time1 日期1
     * @param time2 日期2
     * @return
     */
    public static int getDaysBetween(LocalDateTime time1,LocalDateTime time2) {
        if (time1 == null || time2 == null || time1.getYear() != time2.getYear()) {
            throw new IllegalArgumentException("参数错误");
        }
        return Math.abs(time1.getDayOfYear() - time2 .getDayOfYear());
    }


}
