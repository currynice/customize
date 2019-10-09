package com.cxy.customize.core.math.applications;

import com.cxy.customize.core.math.NumberSystemUtil;



/**
 * 位操作
 */
public class BitOperations {

    private BitOperations() {

    }

    /**
     * 判断num是否为奇数,奇数二进制最后一位总是1,与1进行位与运算得到最后一位
     * @param num
     * @return true 奇数 false 偶数
     */
    public static boolean oddEven(int num){
        return NumberSystemUtil.and(num,1)==1;
    }

    /**
     *
     * 利用异或特性进行交换
     * 两个相等的数疑异或为0
     * 一个数和0异或后不变
     * @param a
     * @param b
     * @return  [b,a]
     */
    public static int[] exchanged(int a,int b){
        a = NumberSystemUtil.xor(a,b);
        b = NumberSystemUtil.xor(a,b);
        a = NumberSystemUtil.xor(a,b);
        return new int[]{a,b};
    }


    /**
     * 交集:按位与 ,1代表交集
     * @param args
     */


    /**
     * 并集:按位或 ,1代表并集
     * @param args
     */



    public static void main(String args []){
        System.out.println(oddEven(-10));
        System.out.println(oddEven(0));
        System.out.println(oddEven(-5));
        int[] arr = exchanged(2,3);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
