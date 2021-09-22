package com.cxy.customize.concurrent;

/**
 * Description:   </br>
 * Date: 2021/9/18 16:22
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class SafeCounter {
    private int count;


    public synchronized void counter(){
        count++;
    }


    public synchronized int getCount(){

        return count;
    }

}
