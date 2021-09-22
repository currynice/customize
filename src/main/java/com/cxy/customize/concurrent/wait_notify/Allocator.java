package com.cxy.customize.concurrent.wait_notify;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:   </br>
 * Date: 2021/9/18 17:43
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
class Allocator {
    //长在使用的资源
    private List<Object> als = new ArrayList<>(2);

    // 一次性申请所有资源
    synchronized boolean apply(Object from, Object to){
        System.out.println(from+"执行一次");
        while(als.contains(from) || als.contains(to)){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace(); //todo
            }
        }

        als.add(from);
        als.add(to);
        return true;
    }
    // 归还资源
    synchronized void free(
            Object from, Object to){
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}

