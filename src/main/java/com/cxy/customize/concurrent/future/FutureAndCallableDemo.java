package com.cxy.customize.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Description:   </br>
 * Date: 2021/9/29 17:02
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class FutureAndCallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> taskTemplate = ()->{
           log.info("进入Callable#call");
            // 模拟⼦线程任务，睡眠 5s，
            Thread.sleep(5000);
            return "call done";
        };


        log.info("提交 Callable 到线程池");
        long startTime = System.nanoTime();
        Future<String> future = executorService.submit(taskTemplate);
        log.info("主线程继续执⾏");
        log.info("主线程等待获取 Future 结果");
        while (!future.isDone()){
            //sleep one seconds ,then recheck
            log.info("Task is still not done...");
            Thread.sleep(1000);

            double elapsedTimeInSec = (System.nanoTime() - startTime)/1000_000_000.0;

            //如果程序运⾏时间⼤于 1s，则取消⼦线程的运⾏
            if(elapsedTimeInSec > 1) {
                future.cancel(true);
            }

        }

        if (!future.isCancelled()){
            log.info("⼦线程任务已完成");
            // Future.get() will blocks until the result is available,
            // here is aim to to avoid block to much time
            String result = future.get();
            log.info("主线程获取到 Future 结果: {}", result);
        }else {
            log.warn("⼦线程任务被取消");
        }

        executorService.shutdown();

    }
}
