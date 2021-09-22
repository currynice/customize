package com.cxy.customize.concurrent;

/**
 * Description:   </br>
 * Date: 2021/9/18 16:40
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class UnsafeCounter {
    private static int count;

    public synchronized void counter(){
        count++;
    }

    public static synchronized int calc(){
        return count++;
    }
}
