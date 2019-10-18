package com.cxy.customize.cache.impl.test;


import com.cxy.customize.cache.impl.AbstractCache;
import com.cxy.customize.cache.impl.CacheObj;
import com.cxy.customize.core.map.FixedLinkedHashMap;

/**
 *
 * @param <K> 键
 * @param <V> 值
 */
public class HashLRUCache<K,V> extends AbstractCache<K, V> {

    /**
     * 构造<br>
     * 默认无超时
     * @param capacity 容量
     */
    public HashLRUCache(int capacity) {
        this(capacity, 0);
    }

    /**
     * 构造
     * @param capacity 容量
     * @param timeout 默认超时时间，单位：毫秒
     */
    public HashLRUCache(int capacity, long timeout){
        if(Integer.MAX_VALUE == capacity) {
            capacity -= 1;
        }

        this.capacity = capacity;
        this.timeout = timeout;

        //链表key按照访问顺序排序，调用get方法后，会将这次访问的元素移至头部
        cacheMap = new FixedLinkedHashMap<K, CacheObj<K, V>>(capacity);
    }

    @Override
    protected int slimCache() {
        return 0;
    }



}
