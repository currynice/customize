package com.cxy.customize.concurrent.problems.visibility;

/**
 * 一个线程对共享变量进行修改，另一个线程可以看得到，称为可见性
 *
 * 多核CPU,线程A操作的是CPU-1上的缓存，而线程B操作的是CPU-2上的缓存，很明显，这个时候线程A对内存中共享变量的操作对于线程B而言就不具备可见性了
 */
public class VisibilityTest {


    private static  long count = 0;

    private void add10K(){
        int index = 0;
        while(index++ < 10000){
            count+=1;
        }
    }


    private static long calr() throws InterruptedException {
        VisibilityTest test = new VisibilityTest();
        Thread thread1 = new Thread(()->{
            test.add10K();
        });

        Thread thread2 = new Thread(()->{
            test.add10K();
        });
        //启动
        thread1.start();
        thread2.start();
        //等待结束
        thread1.join();
        thread2.join();
        return count;
    }

    public static void main(String args[]){
        try {
           System.out.println(calr());//输出10000 - 20000之间的随机数
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
