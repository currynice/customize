package com.cxy.customize.core.math;



import com.cxy.customize.core.lang.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 进制转换
 *
 * 数学的ceil 和 floor 方法 上都有一句话：If the argument is NaN or an infinity or positive zero or negative zero, then the result is the same as the argument，
 * 意思为：如果参数是 NaN、无穷、正 0、负 0，那么结果与参数相同，
 * 如果是 -0.0，那么其结果是 -0.0
 */
public class NumberSystemUtil {

    /** 默认除法运算精度 */
    private static final int DEFAUT_DIV_SCALE = 10;


    /**
     * 十进制转二进制
     * @param decimalSource
     * @return
     */
    public static String decimal2Binary(int decimalSource){
        BigInteger bigInteger = new BigInteger(String.valueOf(decimalSource));
        return bigInteger.toString(2);
    }


    /**
     * 二进制转十进制
     * @param binargSource
     * @return
     */
    public static int binary2Decimal(String binargSource){
        BigInteger bigInteger = new BigInteger(binargSource,2);
        return Integer.valueOf(bigInteger.toString());//默认转换成十进制
    }


    /**
     * 逻辑左移 相当于乘(2^m)
     * @Description: 向左移位
     *
     *  110101  左移1位时  当数字不溢出的情况下， 末尾加1位0  1101010
     *              当二进制位数超过系统指定的位数时，需要将溢出的高位去除
     * @param num- 等待移位的十进制数, m- 向左移的位数
     * @return int- 移位后的十进制数
     */
    public static int leftShift(int num, int m) {
        return num << m;
    }


    /**
     * 算数右移(负数除法使用)
     */
    public static int rightShift2(int num, int m) {
        return num >> m;
    }



    /**
     * 逻辑右移 相当于除以(2^m)的整数商
     * @Description: 向右移位
     *
     * 110101  右移1位    去除未尾1位  11010
     *
     * @param num- 等待移位的十进制数, m- 向右移的位数
     * @return int- 移位后的十进制数
     */
    public static int rightShift(int num, int m) {
        return num >>> m;
    }



    public static void main(String args[]){

        System.out.println(rightShift2(-53,1));
    }
    //bit operation 位操作
    /**
     * 或
     * 有1真为真1
     * @param num1
     * @param num2
     * @return
     */
    public static int or(int num1, int num2) {

        return (num1 | num2);

    }

    /**
     * 与
     * 有0假为假0
     * 将数字和1进行与运算，得到数字的最后一位，最后一位为0是偶数，1是奇数，比对2取模快点
     * @param num1
     * @param num2
     * @return
     */
    public static int and(int num1, int num2) {

        return (num1 & num2);

    }

    /**
     * 异或
     * 排异，不同为1，0表示相同
     * @param num1
     * @param num2
     * @return
     */
    public static int xor(int num1, int num2) {

        return (num1 ^ num2);

    }

    /**
     * 非
     * 0变成1,  1变成0
     * @param num1
     * @return
     */
    public static int not(int num1) {

        return (~num1);

    }

    /**
     * 找到中间数
     * 避免(min+max)/2  出现溢出
     * @param min
     * @param max
     * @return
     */
    public static int middle(int min,int max) {
        Assert.isTrue(min<=max,"min  is forced to be less than max ");
        return min+(max-min)/2;
    }


    //exact division  精确除法start


    //常用的四舍五入
    public static BigDecimal exactDiv(double v1, double v2) {
        return exactDiv(v1,v2,DEFAUT_DIV_SCALE,RoundingMode.HALF_UP);
    }

    public static BigDecimal exactDiv(String v1, String v2) {
        return exactDiv(v1,v2,DEFAUT_DIV_SCALE,RoundingMode.HALF_UP);
    }


    public static BigDecimal exactDiv(double v1, double v2, int scale, RoundingMode roundingMode) {
        return exactDiv(String.valueOf(v1),String.valueOf(v2),scale,roundingMode);
    }

    public static BigDecimal exactDiv(Double v1, Double v2, int scale, RoundingMode roundingMode) {
        return exactDiv(Double.toString(v1),Double.toString(v2),scale,roundingMode);
    }

    public static BigDecimal exactDiv(Number v1, Number v2, int scale, RoundingMode roundingMode) {
        return exactDiv(v1.toString(),v2.toString(),scale,roundingMode);
    }

    /**
     * 提供(相对)精确的除法运算,除不尽的情况时,由scale指定精确度
     *
     * @param v1  使用{@link BigDecimal#BigDecimal(String)}避免精度丢失
     * @param v2
     * @param scale
     * @param roundingMode {@link RoundingMode} 舍入模式
     * @return
     */
    public static BigDecimal exactDiv(String v1, String v2, int scale, RoundingMode roundingMode) {
       return exactDiv(new BigDecimal(v1),new BigDecimal(v2),scale,roundingMode);
    }

    /**
     * 提供(相对)精确的除法运算,除不尽的情况时,由scale指定精确度
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 精确度 取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 商
     */
    public static BigDecimal exactDiv(BigDecimal v1, BigDecimal v2, int scale, RoundingMode roundingMode) {
        Assert.notNull(v2, "0不能作为被除数");
        if (null == v1) {
            return BigDecimal.ZERO;
        }
        scale = Math.abs(scale);
        return v1.divide(v2, scale, roundingMode);
    }
    //exact division  精确除法end



    public static byte charToAscii(char ch) {
        return (byte)ch;
    }


    public static int charToAsciiValue(char ch) {
        return (int)ch;
    }




}
