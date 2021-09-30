package com.cxy.customize.concurrent.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:   </br>
 * Date: 2021/9/30 14:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runAsync();
//        supplyAsync();
        realAsync();
    }


    static void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //手动结束一个Future
        completableFuture.complete("(Future's Result Here Manually");
        String result = completableFuture.get();
    }

    static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("运⾏在⼀个单独的线程当中");
        });
        completableFuture.get();
    }

    static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "supplyAsync";
        });
        System.out.println(completableFuture.get());
    }


    static void realAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> comboText = CompletableFuture.supplyAsync(() -> {
            //可以注释掉做快速返回 start
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
//            log.info("log:赞");
            return "赞";
        }).thenApply(first -> {
//            log.info("log:在看");
            return first + ", 在看";

        }).thenApply(second -> {
//            log.info("log:转发");
            return   second + ", 转发";
        });
        log.info("三连?");
        log.info(comboText.get());
    }
}
