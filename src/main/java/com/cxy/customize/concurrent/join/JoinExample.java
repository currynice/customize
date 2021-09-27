package com.cxy.customize.concurrent.join;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:   </br>
 * Date: 2021/9/27 11:06
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class JoinExample {


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("Thread-1 执⾏完毕");
            }
        }, "Thread-1");
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("Thread-2 执⾏完毕");
            }
        }, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log.info("主线程执⾏完毕");
    }
}
