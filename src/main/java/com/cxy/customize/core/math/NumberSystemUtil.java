package com.cxy.customize.core.math;

import cn.hutool.core.util.StrUtil;

import java.math.BigInteger;

/**
 * 进制转换
 */
public class NumberSystemUtil {
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
        return Integer.valueOf(bigInteger.toString());
    }


    /**
     * 相当于乘2
     * @Description: 向左移位
     * @param num- 等待移位的十进制数, m- 向左移的位数
     * @return int- 移位后的十进制数
     */
    public static int leftShift(int num, int m) {
        return num << m;
    }


    /**
     * 逻辑右移动相当于除2
     * @Description: 向右移位
     * @param num- 等待移位的十进制数, m- 向右移的位数
     * @return int- 移位后的十进制数
     */
    public static int rightShift(int num, int m) {
        return num >>> m;
    }

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
     * @param num1
     * @param num2
     * @return
     */
    public static int and(int num1, int num2) {

        return (num1 | num2);

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


    public static byte charToAscii(char ch) {
        return (byte)ch;
    }


    public static int charToAsciiValue(char ch) {
        return (int)ch;
    }


    public static void main(String args[]){


        System.out.println((charToAsciiValue('h')));
        System.out.println((charToAsciiValue('e')));
        System.out.println((charToAsciiValue('l')));
        System.out.println((charToAsciiValue('l')));
        System.out.println((charToAsciiValue('o')));

        System.out.println(decimal2Binary(charToAsciiValue('h')));
        System.out.println(decimal2Binary(charToAsciiValue('e')));
        System.out.println(decimal2Binary(charToAsciiValue('l')));
        System.out.println(decimal2Binary(charToAsciiValue('l')));
        System.out.println(decimal2Binary(charToAsciiValue('o')));

    }


}
