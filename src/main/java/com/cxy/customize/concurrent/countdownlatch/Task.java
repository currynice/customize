package com.cxy.customize.concurrent.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:   </br>
 * Date: 2021/9/27 15:03
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class Task {

    void main() throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++){
            executorService.execute(new SubTask(doneSignal,i));
        }
        //等所有子任务执行完成
        doneSignal.await();
        log.info("all done ");
        executorService.shutdown();

    }


    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        task.main();
    }

}
