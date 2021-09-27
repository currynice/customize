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
public class Worker implements Runnable{

    private CountDownLatch startSignal;

    private CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {


        try {
            //开始信号，阻⽌任何 Worker ⼯作，直到 Driver 准备好
            startSignal.await();
            working();
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    void working(){
        log.info(Thread.currentThread().getName()+":do self business");
    }
}
