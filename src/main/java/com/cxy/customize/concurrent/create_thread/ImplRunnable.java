package com.cxy.customize.concurrent.create_thread;

/**
 * Description:   </br>
 * Date: 2021/9/22 11:22
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ImplRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":实现Runnable接口创建线程");
    }
}


class ThreadTest2{
    public static void main(String[] args) {
        Runnable task = new ImplRunnable();
        new Thread(task).start();
        new Thread(task).start();
    }
}