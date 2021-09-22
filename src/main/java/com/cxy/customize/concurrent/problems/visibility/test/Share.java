package com.cxy.customize.concurrent.problems.visibility.test;

import com.cxy.customize.concurrent.problems.visibility.StartExample;

/**
 * Description:   </br>
 * Date: 2021/9/18 15:06
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Share {

    private int a;


    public static void main(String[] args) {
        Share share = new Share();
        Thread thread1 = new Thread(share::changeA,"Thread-A");
        thread1.start();
        System.out.println("主线程结束");
    }


    public void changeA(){
        a=10;
    }


}
