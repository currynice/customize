package com.cxy.customize.concurrent;

/**
 * Description:   </br>
 * Date: 2021/9/18 16:16
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ValidLock {
    private static final Object object = new Object();
    private int count;
    public synchronized void badSync(){
        //其他与共享变量count⽆关的业务逻辑
        count++;
    }


    public void goodSync(){
        //其他与共享变量count⽆关的业务逻辑
        synchronized (object){
            count++;
        }
    }
}
