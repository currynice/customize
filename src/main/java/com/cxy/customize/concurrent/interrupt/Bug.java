package com.cxy.customize.concurrent.interrupt;

/**
 * Description:   </br>
 * Date: 2021/9/23 17:58
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Bug {


    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                //未重置中断状态
                break;
            }
            // 省略业务代码
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
