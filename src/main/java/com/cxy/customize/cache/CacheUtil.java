package com.cxy.customize.cache;



import com.cxy.customize.cache.impl.LRUCache;
import com.cxy.customize.core.date.DateUnit;

public class CacheUtil {

    private CacheUtil() {
    }

    /**
     * 创建LRU (least recently used)最近最久未使用缓存.
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @param capacity 容量
     * @param timeout 过期时长，单位：毫秒
     * @return {@link LRUCache}
     */
    public static <K, V> LRUCache<K, V> newLFUCache(int capacity, long timeout){
        return new LRUCache<K, V>(capacity, timeout);
    }

    /**
     * 创建LRU (least recently used)最近最久未使用缓存.
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @param capacity 容量
     * @return {@link LRUCache}
     */
    public static <K, V> LRUCache<K, V> newLRUCache(int capacity){
        return new LRUCache<K, V>(capacity);
    }


    public static void main(String args[]) throws InterruptedException {
        Cache<String, String> lruCache = CacheUtil.newLRUCache(3);
        lruCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lruCache.get("key1");//使用了
        lruCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

//由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2被移除）
        System.out.println(lruCache.get("key1"));
        System.out.println(lruCache.get("key2"));//null
        System.out.println(lruCache.get("key3"));
        System.out.println(lruCache.get("key4"));

        Thread.sleep(5000);

        System.out.println(lruCache.get("key1"));
        System.out.println(lruCache.get("key2"));//null
        System.out.println(lruCache.get("key3"));
        System.out.println(lruCache.get("key4"));
    }
}
