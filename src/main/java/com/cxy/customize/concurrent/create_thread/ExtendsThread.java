package com.cxy.customize.concurrent.create_thread;

/**
 * Description:   </br>
 * Date: 2021/9/22 11:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ExtendsThread extends Thread {
    @Override
    public void run(){
        System.out.println(this.getName()+":用Thread类实现线程");
    }
}



 class ThreadTest {

    public static void main(String[] args) {
        ExtendsThread thread1 = new ExtendsThread();
        thread1.start();
    }

}

