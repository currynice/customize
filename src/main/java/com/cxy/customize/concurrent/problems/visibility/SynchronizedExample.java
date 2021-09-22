package com.cxy.customize.concurrent.problems.visibility;

/**
 * Description:   </br>
 * Date: 2021/9/18 14:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class SynchronizedExample {

    private int x = 0;

    public void synBlock(){
        synchronized (SynchronizedExample.class) {
            x=1;
        }
    }


    public synchronized void syncMethod(){
        x=2;
    }

}
