package com.cxy.customize.core.date;

import cn.hutool.core.lang.Assert;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


/**
 * 某一天的时间间隔 类
 *
 * @author cxy
 *
 */
public class TimeBetween implements Serializable {

        private static final long serialVersionUID = 1L;

        /**日期*/
        private LocalDate date;

        /** 开始时间 */
        private LocalTime beginTime;
        /** 结束时间 */
        private LocalTime endTime;

        /**
         * 静态构造方法0
         * 起始时间在前，结束时间在后 时间间隔为正数
         *
         * @param begin 起始时间
         * @param end 结束时间
         * @param date 日期参数
         * @return {@link TimeBetween}
         */
        public static TimeBetween create(LocalTime begin, LocalTime end,LocalDate date) {
            return new TimeBetween(begin, end,date);
        }

        /**
         * 静态构造方法1
         * 起始时间在前，结束时间在后
         *
         * @param begin 起始时间
         * @param end 结束时间
         * @param date 日期参数
         * @param isAbs 日期间隔还是正数
         * @return {@link TimeBetween}
         */
        public static TimeBetween create(LocalTime begin, LocalTime end,LocalDate date, boolean isAbs) {
            return new TimeBetween(begin, end,date, isAbs);
        }

        /**
         * 构造器0
         * 起始时间在前，结束时间在后 时间间隔为正数
         *
         * @param begin 起始时间
         * @param end 结束时间
         * @param date 日期参数
         */
        public TimeBetween(LocalTime begin, LocalTime end, LocalDate date) {
            this(begin, end, date, true);
        }


    /**
     * 构造器1
     * 起始时间在前，结束时间在后
     *
     * @param begin 起始时间
     * @param end 结束时间
     * @param date 日期参数
     * @param isAbs 日期间隔还是正数
     */
    public TimeBetween(LocalTime begin, LocalTime end, LocalDate date, boolean isAbs) {
        //参数校验,出错抛异常
        Assert.notNull(date, "日期为null!");
        Assert.notNull(begin, "开始时间为null!");
        Assert.notNull(end, "结束时间null !");

        this.date = date;
        if (isAbs && begin.isAfter(end)) {
            // 间隔为正时，传参错了换一下
            this.beginTime = end;
            this.endTime = begin;
        } else {
            this.beginTime = begin;
            this.endTime = end;
        }
    }

    /**
     * 判断与另一个时间段是否重叠
     * 1: (begin1 BETWEEN begin2 AND end2)
     *
     * 2. (end1 BETWEEN begin2 AND end2)
     *
     * 3. (begin2 BETWEEN begin1 AND end1)
     *
     * 4 (end2 BETWEEN begin1 AND end1)
     *
     * 小总结: begin1 <=end2 && end1>=begin2
     *
     * @param b  另一个时间段 b
     * @return  true:重叠 false:没重叠
     */
    public  boolean overlapping(TimeBetween b){
        //日期不一样，肯定不重叠
        if(!this.date.equals(b.date)){
            return false;
        }
        return (this.beginTime.isBefore(b.endTime))&&(this.endTime.isAfter(b.beginTime)) ;
    }


        /**
         * 判断两个时间相差的时长<br>
         * 返回 给定单位的时长差
         *
         * @param unit 单位：相差 小时{@link ChronoUnit#HOURS}、分钟{@link ChronoUnit#MINUTES} 。。。
         * @return 时长差
         */
        public long between(ChronoUnit unit) {
          return this.beginTime.until(endTime, unit);
        }


     /**
      * 按分钟为单位输出
      * @return
      */
        @Override
        public String toString() {
            return between(ChronoUnit.MINUTES)+"分";
        }




        public static void main(String args[]){
            LocalDate localDate = LocalDate.now();//2020/03/04
            LocalTime begin = LocalTime.of(8,0,0);
            LocalTime end = LocalTime.of(12,0,0);
            TimeBetween timeBetween = TimeBetween.create(begin,end,localDate);


            LocalDate localDate2 = localDate;//2020/03/04
            LocalTime begin2 = LocalTime.of(12,5,0);
            LocalTime end2 = LocalTime.of(13,0,0);
            TimeBetween timeBetween2 = TimeBetween.create(begin2,end2,localDate2);
            System.out.println(timeBetween.overlapping(timeBetween2));
        }
    }
