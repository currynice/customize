package com.cxy.customize.core.util;

import cn.hutool.cron.pattern.CronPatternUtil;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static com.cxy.customize.core.util.DateUtil.date2LocalDate;
import static com.cxy.customize.core.util.DateUtil.getTommorow;


/**
 *
 * @description Corn表达式工具类
 * @author chengxinyu
 * @date 2019年7月28日下午2:42:24
 * @version 1.1.0
 */
public class CronUtil {
    private CronUtil() {

    }

    public enum PeriodEnum {
        /**
         * 每周
         */
        WEEKLY(0),
        /**
         * 每月
         */
        MONTH(1),
        /**
         * 每季
         */
        QUARTERLY(2),
        /**
         * 每年
         */
        YEARLY(3);

        private final int value;//状态值

        //构造方法
        PeriodEnum(int value) {
            this.value = value;
        }

        public static PeriodEnum getPeriod(int value) {
            for (PeriodEnum us : PeriodEnum.values()) {
                if (value == (us.getValue())) {
                    return us;
                }
            }
            return null;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 根据日期得到Cron表达式
     * <strong>业务需要，记得将上层触发器开始时间定为checkDate后一天</strong>
     *
     * <strong>quartz不支持day-of-week AND a day-of-month 同时为 * </strong>
     *
     * @param checkDate 日期
     * @param period    周期
     * @return
     */
    public static String createCronExpression(Date checkDate, PeriodEnum period) {

        StringBuffer croExp = new StringBuffer();
        //Date  --> LocalDate
        croExp.append("0 ");
        LocalDate localDate = date2LocalDate(checkDate);
        int dayofWeek = localDate.getDayOfWeek().getValue();//周几
        int dayOfMonth = localDate.getDayOfMonth();//每月几号
        int month = localDate.getMonthValue();// 几月

        switch (period) {
            case WEEKLY:
                //按周执行：每周三 1点执行：0 0 1 * * 3 *

                croExp.append("0 ");//分钟
                croExp.append("1 ");//小时

                croExp.append("? * " + dayofWeek + " *");
                break;
            case MONTH:
                //按月执行：每月15号1点执行：0 0 3 15 * ？ *  (31号的话，改成30号)
                croExp.append("0 ");//分钟
                croExp.append("1 ");//小时
                //闰年229 或者31号，设置为每月最后一天
                if (DateUtil.isSpecialDate(localDate) || dayOfMonth == 31) {
                    croExp.append("L * ? *");//最后一天
                } else {
                    croExp.append(dayOfMonth + " * ? *");
                }
                break;
            case QUARTERLY:
                //按季度执行：7月18号开始每三个月 的18号1点 执行一次：0 0 1 18 7,10,1,4 ？ *
                //指定4个执行月
                int month1 = month;
                int month2 = (month1 + 3) % 12 == 0 ? 12 : (month1 + 3) % 12;
                int month3 = (month2 + 3) % 12 == 0 ? 12 : (month2 + 3) % 12;
                int month4 = (month3 + 3) % 12 == 0 ? 12 : (month3 + 3) % 12;

                croExp.append("0 ");//分钟
                croExp.append("1 ");//小时
                if (DateUtil.isSpecialDate(localDate) || dayOfMonth == 31) {
                    croExp.append("L " + month1 + "," + month2 + "," + month3 + "," + month4 + " ? *");
                } else {
                    croExp.append(dayOfMonth + " " + month1 + "," + month2 + "," + month3 + "," + month4 + " ? *");
                }
                break;
            case YEARLY:
                //按年执行：每年6月1号1点执行：0 0 1 1 6 ？ *
                croExp.append("0 ");//分钟
                croExp.append("1 ");//小时
                if (DateUtil.isSpecialDate(localDate) || dayOfMonth == 31) {
                    croExp.append("L " + month + " ? *");
                } else {
                    croExp.append(dayOfMonth + " " + month + " ? *");
                }

                break;
            default:

                break;
        }
        return croExp.toString();
    }

    /**
     * 使用CornExpression 对表达式进行校验
     * 主要是 4:Day-of-Month 和 6：Day-of-Week不可同时为 *
     */
//    public static boolean islegalCron(String cron){
//        return CronExpression.isValidExpression(cron);
//    }
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        String start = "2019/08/02";
        String end = "2024/03/02";
        try {
            Date checkDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            Date tommor = getTommorow(checkDate);
            System.out.println(CronUtil.createCronExpression(checkDate, PeriodEnum.YEARLY));
            //System.out.println(islegalCron(CronUtil.createCronExpression(checkDate, PeriodEnum.YEARLY)));

            CronPatternUtil.matchedDates(CronUtil.createCronExpression(checkDate, PeriodEnum.YEARLY), tommor, endDate, 5, true).forEach(o -> System.out.println(o.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
