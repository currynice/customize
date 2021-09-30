package com.cxy.customize.CompletionService;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:   </br>
 * Date: 2021/9/30 15:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Slf4j
public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        usingExecutorService();
        usingCompletionService();

    }


    private static void usingExecutorService() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> futures = new ArrayList<>();
        futures.add(executorService.submit(taskTemplate(5,"A")));
        futures.add(executorService.submit(taskTemplate(3,"B")));
        futures.add(executorService.submit(taskTemplate(2,"C")));
        futures.add(executorService.submit(taskTemplate(6,"D")));

        // 遍历 Future list，通过 get() ⽅法获取每个 future 结果
        for (Future<String> future : futures) {
            String result = future.get();
            // 其他业务逻辑
            System.out.println(result);

        }

        executorService.shutdown();
    }

    private static void usingCompletionService() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // ExecutorCompletionService 是 CompletionService 唯⼀实现类
        CompletionService executorCompletionService = new ExecutorCompletionService<>(executorService);
        List<Future<String>> futures = new ArrayList<>();
        futures.add(executorCompletionService.submit(taskTemplate(5,"A")));
        futures.add(executorCompletionService.submit(taskTemplate(3,"B")));
        futures.add(executorCompletionService.submit(taskTemplate(2,"C")));
        futures.add(executorCompletionService.submit(taskTemplate(6,"D")));

        // 遍历 Future list，通过 get() ⽅法获取每个 future 结果
        for (int i = 0; i < futures.size(); i++) {
            String result = ((Future<String>) executorCompletionService.take()).get();
            // 其他业务逻辑
            System.out.println(result);
        }

        executorService.shutdown();
    }


    static Callable<String> taskTemplate(long minutesToSleep,String taskName) {
        return ()->{
                // 模拟⼦线程任务，睡眠 5s，
                TimeUnit.SECONDS.sleep(minutesToSleep);
            return taskName+"call done";
        };
    }




}
