package com.cxy.customize.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:   </br>
 * Date: 2021/9/24 9:13
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Sample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
//        ...
        }finally{
            lock.unlock();
        }

    }
}
