package com.cxy.customize.bloom.bitMap;

/**
 * 位图接口
 * 提供两种实现(int long),分别针对32位和64位系统
 */
public interface BitMap {

    //位图操作
    enum BitOperation {
        AND, OR, XOR, NOT;
    }

    public final int MACHINE32 = 32; //int 是32位
    public final int MACHINE64 = 64; //long 64 位

    /**
     * 添加
     * @param k ，
     * @return
     */
    public boolean set(long k);

    /**
     * 检查是否包含值
     * 0 false ///  1 true
     * @param k
     */
    public boolean get(long k);

    /**
     * 移除值
     *
     * @param k
     */
    public void remove(long k);



}
