package com.cxy.customize.concurrent.create_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:   </br>
 * Date: 2021/9/22 11:26
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class CallableTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello";
    }
}

  class ThreadTest3 {

      public static void main(String[] args) {
          //创建异步任务
          FutureTask<String> futureTask = new FutureTask<>(new CallableTask());

          //启动线程
          new Thread(futureTask).start();

          try {
              //任务执行完成返回结果
              String result = futureTask.get();
              System.out.println(result);
          } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
          }
      }

  }

