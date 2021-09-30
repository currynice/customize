package com.cxy.customize.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import javax.print.DocFlavor;
import java.util.concurrent.*;

/**
 * Description: 泡茶期间，洗水壶->烧开水一个线程串行外，洗茶壶，洗茶杯，取茶叶一个线程串行外。   </br>
 * Date: 2021/9/30 11:03
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class AsyncWithingFuture2 {


    /**
     * 线程1 中获取线程2的执行结果
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);


        // 创建线程2的FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());

        // 创建线程1的FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        executorService.submit(ft1);
        executorService.submit(ft2);
        log.info(ft1.get() + ft2.get());
        log.info("开始泡茶");
        executorService.shutdown();

    }

    /**
     * 因任务1 执行时间更长，
     */
    static class T1Task implements Callable<String> {

        private FutureTask<String> ft2;
        public T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }


        @Override
        public String call() throws Exception {
            log.info("T1:洗⽔壶...");
            TimeUnit.SECONDS.sleep(1);
            log.info("T1:烧开⽔...");
            TimeUnit.SECONDS.sleep(15);

            String t2Result = ft2.get();
            log.info("T1 拿到T2的 {}， 开始泡茶", t2Result);
            return "T1: 上茶！！！";
        }
    }
    static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);
            log.info("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);
            log.info("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "T2:福鼎⽩茶拿到了";
        }
    }
}
