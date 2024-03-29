package com.cxy.customize.concurrent.problems.visibility;

/**
 * Description:   </br>
 * Date: 2021/9/18 14:26
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class JoinExample {

    private int x = 0;
    private int y = 1;
    private boolean flag = false;


    public static void main(String[] args) throws InterruptedException {
        JoinExample joinExample = new JoinExample();

        Thread thread1 = new Thread(joinExample::writer, "线程1");

        thread1.start();
        thread1.join();

        System.out.println("x:" + joinExample.x );
        System.out.println("y:" + joinExample.y );
        System.out.println("flag:" + joinExample.flag );
        System.out.println("主线程结束");
    }


    public void writer(){
        this.x = 100;
        this.y = 200;
        this.flag = true;
    }
}
