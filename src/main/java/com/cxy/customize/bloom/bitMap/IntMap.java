package com.cxy.customize.bloom.bitMap;

/**
 * 乞丐版位图:利用int数组保存数据
 *
 *
 */
public class IntMap implements BitMap {
    // Java 中 int 类型占 32bit，也即是 4 个字节
    private int[] ints;

    /**
     * 构造，93750000表示bit数
     */
    public IntMap() {
        ints = new int[93750000];
    }

    /**
     * set: 位数组第三位为1,想要继续set(10)
     * 初始:                   000000000000000000000000001000
     * 将1右移10位:            000000000000000000010000000000
     * 进行或(有真为真)操作的: 000000000000000000010000001000   ----> 成功将位数组 3和 10 两个位上都为1了
     */
    @Override
    public boolean set(long k) {
        //除32，得到数组对应index,也就是右移动5位,byte3位，char4,long6...
        //int intIndex = (int)(knum >> 5)
        int intIndex = (int)(k / MACHINE32);
        int bitIndex =(int)( k % MACHINE32);
        ints[intIndex] |=(1 << bitIndex);
        return true;
    }






/**
 * get: 位数组第三位,第十位为1,想要继续知道第十位,和第六位是不是1
 * 初始:                       000000000000000000010000001000    （1）
 * 将1右移10位:                000000000000000000010000000000     （2）
 * 将1右移6位:                 000000000000000000000001000000      （3）
 * （1）(2)取与:               000000000000000000010000000000   ----> 这个数不是0，表示第十位是1
 * （1）(3)取与:               000000000000000000000000000000   ----> 这个数是0，表示第六位不是1
 *
 */

    @Override
    public boolean get(long k) {
        int intIndex = (int)k / MACHINE32;
        int bitIndex =  (int)k % MACHINE32;
        return (ints[intIndex] & (1 << bitIndex)) !=0;//  !=0很关键哦,不是==1哦
    }





    /**
     * remove: 位数组第三位,第十位为1,想要继续remove(3)
     * 初始:                       000000000000000000010000001000
     * 将1右移3位:                 000000000000000000000000001000
     * 将1右移3位取非:             111111111111111111111111110111
     * 初始与(将1右移3位取非)取与: 000000000000000000010000000000   ----> 成功将位数组 3变为0了
     */

    @Override
    public void remove(long k) {
        int intIndex = (int) (k /MACHINE32);
        int bitIndex = (int) (k % MACHINE32);
        ints[intIndex] &= ~(1 << bitIndex);
    }
}

