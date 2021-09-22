package com.cxy.customize.concurrent.problems.visibility;

/**
 * Description:   </br>
 * Date: 2021/9/17 11:20
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ReorderExample {
    int x = 0;
    int y = 1;
    volatile boolean flag = false;

    public void writer() {
        x = 42; //1
        y = 50; //2
        flag = true; //3
    }


    public void reader() {
        if (flag) { //4
            System.out.println("x:"+x); //5
            System.out.println("y:"+y); //6
        }
    }
}