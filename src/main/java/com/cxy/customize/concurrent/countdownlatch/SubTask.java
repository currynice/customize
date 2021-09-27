package com.cxy.customize.concurrent.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * Description:   </br>
 * Date: 2021/9/27 14:47
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class SubTask implements Runnable {

    private final CountDownLatch doneSignal;
    private final int i;


    SubTask(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            doWork(i);
            doneSignal.countDown();
        } catch (InterruptedException ex) {

        } // return;
    }

    void doWork(int i) throws InterruptedException{
        Thread.sleep(1000L);
        log.info(i+"done");

    }
}
