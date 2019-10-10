package com.cxy.customize.bloom;

import java.io.*;

public class BloomUtil {

    /**
     * 创建一个BitSet实现的布隆过滤器，过滤器的容量为c * n 个bit.
     *
     * @param c 当前过滤器预先开辟的最大包含记录,通常要比预计存入的记录多一倍.
     * @param n 当前过滤器预计所要包含的记录.
     * @param k 哈希函数的个数，等同每条记录要占用的bit数.
     * @return BitSetBloomFilter
     */
    public static BitSetBloomFilter createBitSet(int c, int n, int k) {
        return new BitSetBloomFilter(c, n, k);
    }

    /**
     * 创建BitMap实现的布隆过滤器
     *
     * @param m BitMap的大小
     * @return BitMapBloomFilter
     */
    public static BitMapBloomFilter createBitMap(int m) {
        return new BitMapBloomFilter(m);
    }

    public static void main(String args[]) throws IOException {
        BitSetBloomFilter bitSetBloomFilter = BloomUtil.createBitSet(100,36,3);
        bitSetBloomFilter.init("file/bloomFilter/init.txt","utf-8");
        System.out.println(bitSetBloomFilter.contains("liv"));
    }

}
