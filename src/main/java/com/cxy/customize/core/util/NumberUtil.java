package com.cxy.customize.core.util;



import com.cxy.customize.core.lang.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class NumberUtil {


    /**
     * 保留固定位数小数<br>
     * 例如保留四位小数：123.456789 =》 123.4567
     *
     * @param v 值
     * @param scale 保留小数位数
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 新值
     */
    public static BigDecimal round(double v, int scale, RoundingMode roundingMode) {
        return round(Double.toString(v), scale, roundingMode);
    }


    /**
     * 保留固定位数小数<br>
     * 例如保留四位小数：123.456789 =》 123.4567
     *
     * @param numberStr 数字值的字符串表现形式
     * @param scale 保留小数位数，如果传入小于0，则默认0
     * @param roundingMode 保留小数的模式 {@link RoundingMode}，如果传入null则默认四舍五入
     * @return 新值
     */
    public static BigDecimal round(String numberStr, int scale, RoundingMode roundingMode) {
        Assert.notBlank(numberStr);
        if (scale < 0) {
            scale = 0;
        }
        return round(toBigDecimal(numberStr), scale, roundingMode);
    }


    /**
     * 保留固定位数小数
     * @param number
     * @param scale
     * @param mode   默认四舍五入
     * @return
     */
    public static BigDecimal round(BigDecimal number, int scale, RoundingMode mode){
        if(null==number){
            number=BigDecimal.ZERO;
        }
        if(scale<0){
            scale = 0;
        }
        if(null == mode){
            mode = RoundingMode.HALF_UP;
        }

        return number.setScale(scale,mode);

    }

    /**
     * numberstr ->bigDecimal
     * @param numberStr
     * @return
     */
    public static BigDecimal toBigDecimal(String numberStr){
        return StrUtil.isEmpty(numberStr)?BigDecimal.ZERO:new BigDecimal(numberStr);
    }


    /**
     * double ->bigDecimal
     * @param d
     * @return
     */
    public static BigDecimal toBigDecimal(double d){
        return  BigDecimal.valueOf(d);
    }
}
