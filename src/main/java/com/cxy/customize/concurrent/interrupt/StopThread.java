package com.cxy.customize.concurrent.interrupt;

/**
 * Description:   </br>
 * Date: 2021/9/23 17:12
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class StopThread implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && (count < 1000)) {
            // 未被设置中断 且 还有工作
            System.out.println("count = " + count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new StopThread());
        threadA.start();
        Thread.sleep(10);
        threadA.interrupt();
        Thread.sleep(2000);
    }
}

