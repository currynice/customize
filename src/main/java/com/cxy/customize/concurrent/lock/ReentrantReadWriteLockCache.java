package com.cxy.customize.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:   </br>
 * Date: 2021/9/26 14:45
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ReentrantReadWriteLockCache {

    // ⾮线程安全的 HashMap
    static Map<String, Object> map = new HashMap<>();


    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 构建读锁
    static Lock rl = readWriteLock.readLock();
    // 构建写锁
    static Lock wl = readWriteLock.writeLock();


    public static Object get(String key) {
        Object obj = null;

        rl.lock();
        try{
            obj =  map.get(key);
        }finally {
            rl.unlock();
        }
        // 缓存中值不为空，直接返回
        if (obj!= null) {
            return obj;
        }

        // 缓存中值为空，则通过写锁查询DB，并将其写⼊到缓存中
        wl.lock();
        try{
            // 再次尝试获取缓存中的值
            obj = map.get(key);
            // 再次获取缓存中值还是为空
            if (obj == null) {
                // 查询DB
                obj = getDataFromDB(key); // 伪代码：getDataFromDB
                // 将其放⼊到缓存中
                map.put(key, obj);
            }
        }finally {
            wl.unlock();
        }
        return obj;
    }

    private static Object getDataFromDB(String key) {
        return key+"Object";
    }


    public static Object put(String key, Object value){
        wl.lock();
        try{
            return map.put(key, value);
        }finally {
            wl.unlock();
        }
    }


    public static void main(String[] args) {

        int i = 0;
    }

}
