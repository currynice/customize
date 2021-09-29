package com.cxy.customize.concurrent.cyclicBarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:   </br>
 * Date: 2021/9/28 10:07
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class CyclicBarrierDemo {

    // 创建 CyclicBarrier 实例，计数器的值设置为2
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int breakCount = 0;
        // 将线程提交到线程池
        executorService.submit(() -> {
            try {
                log.info(Thread.currentThread() + "第⼀回合");
                Thread.sleep(1000);
                cyclicBarrier.await();
                log.info(Thread.currentThread() + "第⼆回合");
                Thread.sleep(2000);
                cyclicBarrier.await();
                log.info(Thread.currentThread() + "第三回合");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                log.info(Thread.currentThread() + "第⼀回合");
                Thread.sleep(2000);
                cyclicBarrier.await();
                log.info(Thread.currentThread() + "第⼆回合");
                Thread.sleep(1000);
                cyclicBarrier.await();
                log.info(Thread.currentThread() + "第三回合");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
