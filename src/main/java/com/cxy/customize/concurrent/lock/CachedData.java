package com.cxy.customize.concurrent.lock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * Description: a sketch showing how to perform lock downgrading after updating a cache    </br>
 * Date: 2021/9/27 9:54
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class CachedData {


    static Object data;

    //cache-state
    volatile boolean cacheValid;

    final ReentrantReadWriteLock rel = new ReentrantReadWriteLock();

    final ReadLock readLock = rel.readLock();

    final WriteLock writeLock = rel.writeLock();


    void  process(){

        readLock.lock(); //acquire readLock

        if(!cacheValid){
            //must release all readLock before acquiring write-lock
            readLock.unlock();

            //1:first acquire write
            writeLock.lock();

            try{
                //re-check state , because another thread might have acquire write-lock
                // and changed  cacheValid before we did
                if(!cacheValid){
                    data = fromDB();
                    cacheValid = true;
                }
                //2：downgrade by acquiring read-lock before releasing write-lock
                readLock.lock();

            }finally {
                writeLock.unlock(); // 3:release write ,but still hold read
            }

        }

        try {
            System.out.println("data："+data);
            System.out.println("now："+new Date());

        } finally {
            readLock.unlock();
        }

    }

    //that is a bad idea
    private Object fromDB() {
        return new Date();
    }

}
