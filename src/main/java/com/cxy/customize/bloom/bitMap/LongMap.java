package com.cxy.customize.bloom.bitMap;

/**
 * 利用char完成位图逻辑
 *
 *
 */
public class LongMap implements BitMap {
    // Java 中 long 类型占 64bit，也即是 8 个字节
    private long[] longs;



    /**
     * 构造
     */
    public LongMap() {
        longs = new long[93750000];
    }

    public LongMap(int size) {
        longs = new long[size];
    }


    @Override
    public boolean set(long k) {
        int longIndex = (int)k / MACHINE64;
        int bitIndex =(int) k % MACHINE64;
        longs[longIndex] |= (1 << bitIndex);
        return true;
    }

    @Override
    public boolean get(long k) {
        int longIndex = (int)k / MACHINE64;
        int bitIndex =  (int)k % MACHINE64;
        return (longs[longIndex] & (1 << bitIndex)) !=0;
    }




    @Override
    public void remove(long k) {
        int longIndex = (int) (k /MACHINE64);
        int bitIndex = (int) (k % MACHINE64);
        longs[longIndex] &= ~(1 << bitIndex);
    }
}

