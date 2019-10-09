package com.cxy.customize.bloom.bitMap;

/**
 * Test
 */
public class BitMapTest {
    public static void main(String args[]) {
        LongMap intMap = new LongMap();
        intMap.set(255L);
        intMap.set(55L);
        System.out.println(intMap.get(255L));
        System.out.println(intMap.get(55L));
        intMap.remove(55L);
        System.out.println(intMap.get(55L));
    }
}
