package com.cxy.customize.concurrent.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * Description:   </br>
 * Date: 2021/9/27 14:10
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public  class Driver {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        //do driver`s business
        log.info("do driver`s business");
        startSignal.countDown();

        //完成信号 doneSignal ，允许 Driver 等待，直到所有Worker 完成
        doneSignal.await();

    }

}

