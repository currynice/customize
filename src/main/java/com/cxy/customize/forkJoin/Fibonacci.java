package com.cxy.customize.forkJoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

@Slf4j
public class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public Integer compute() {
        //可计算的最⼩单元
        if (n <= 1) {
            return n;
        }
        // 查看当前⼦线程名称
        log.info(Thread.currentThread().getName());

        Fibonacci f1 = new Fibonacci(n - 1);
        // 拆分成⼦任务
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        // f1.join： 等待⼦任务f1的执⾏结果
        return f2.compute() + f1.join();
    } }
