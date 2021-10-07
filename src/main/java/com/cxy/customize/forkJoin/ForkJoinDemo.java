package com.cxy.customize.forkJoin;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

@Slf4j
public class ForkJoinDemo {


    /**
     * 菲波拉切
     * @param args
     */
    public static void main(String[] args) {
        int n = 20;
        //追踪⼦线程名称
        final ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            worker.setName("my-thread"+worker.getPoolIndex());
            return worker;
        };

        //分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4,factory,null,false);

        //分治任务
        Fibonacci fibonacci = new Fibonacci(n);

        //invoke启动
        Integer result = forkJoinPool.invoke(fibonacci);
        log.info("Fibonacci {} 的结果是 {}", n, result);

    }
}
