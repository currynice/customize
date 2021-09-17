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

    boolean flag = false;

    public void writer() {
        x = 42; //1
        flag = true; //2
    }


    public void reader() {
        if (flag) { //3
            System.out.println(x); //4
        }
    }
}