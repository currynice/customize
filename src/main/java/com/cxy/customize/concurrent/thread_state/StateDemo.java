package com.cxy.customize.concurrent.thread_state;

/**
 * Description:   </br>
 * Date: 2021/9/22 9:12
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class StateDemo {

    public static void main(String[] args) throws InterruptedException {
//        new_();
//        blocked_();
//        waiting_();
        time_waiting_();
    }

    private static void new_(){
        Thread t1 = new Thread(()->{}){};
        System.out.println(t1.getState());//NEW

        t1.start();
        System.out.println(t1.getState());//RUNNABLE
    }

    private static void blocked_() throws InterruptedException {

        Thread t1 = new Thread(new DemoThread());
        Thread t2 = new Thread(new DemoThread());
        t1.start();
        t2.start();

        Thread.sleep(1000L);
        System.out.println(t1.getState());//RUNNABLE

        System.out.println(t2.getState());//BLOCKED

    }


    private static void waiting_() throws InterruptedException {
        Thread main = Thread.currentThread();
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(main.getState());
                e.printStackTrace();
            }
            System.out.println(main.getState());
        });
        thread2.start();
        thread2.join();
    }


    private static void time_waiting_() throws InterruptedException{
            Thread thread3 = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // 为什么要调⽤interrupt⽅法？防止生吞中断
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            });
            thread3.start();
            Thread.sleep(1000);
            System.out.println(thread3.getState());
    }




        static class DemoThread implements Runnable{

        @Override
        public void run() {
            commonResource();
        }

        private static synchronized void commonResource() {
            while (true){

            }
        }


    }
}
