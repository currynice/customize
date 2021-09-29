package com.cxy.customize.concurrent.countdownlatch;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:   </br>
 * Date: 2021/9/27 13:25
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class CountDownLatchDemo0 {

    private static CountDownLatch countDownLatch = new CountDownLatch(2);


    public static void main(String[] args) throws InterruptedException {
        //just want a convenient thread-pool
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("Thread-1 执⾏完毕");
                //计数器减1
                countDownLatch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("Thread-2 执⾏完毕");
                //计数器减1
                countDownLatch.countDown();
            }
        });

        log.info("主线程等待⼦线程执⾏完毕");
        log.info("计数器值为：" + countDownLatch.getCount());
//        countDownLatch.await(1000, TimeUnit.MILLISECONDS);
        countDownLatch.await();

        log.info("计数器值为：" + countDownLatch.getCount());
        log.info("主线程执⾏完毕");
        executorService.shutdown();


    }
}
