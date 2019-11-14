package com.cxy.customize.concurrent.problems.visibility;

/**
 * 共享变量x:在线程A中声明为abc,让其对其他线程可见.
 * 使用
 */
public class VisibilityVariable {


    static String x = "default";

    private static void edit() throws InterruptedException {

        VisibilityVariable test = new VisibilityVariable();
        Thread thread1 = new Thread(()->{
          x = "abc";
        });

        Thread thread2 = new Thread(()->{
           System.out.println(x);
        });
        //启动
        thread1.start();
        thread2.start();
        //等待结束
        thread1.join();
        thread2.join();

    }

    public static void main(String args[]){
        try {
            for(int i=0;i<100;i++){
                edit();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
