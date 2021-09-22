package com.cxy.customize.concurrent;

/**
 * Description:   </br>
 * Date: 2021/9/18 15:59
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class SyncDemo {

        private static final Object object = new Object();

        public synchronized void normalSyncMethod(){
            //临界区
        }

        public static synchronized void staticSyncMethod(){
            //临界区
        }

        public void syncBlockMethod(){
            synchronized (object){
                //临界区
            }
        }

}
