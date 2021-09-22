package com.cxy.customize.concurrent.create_thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:   </br>
 * Date: 2021/9/22 13:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class MyTimerTask extends TimerTask {

    private String taskName;

    public MyTimerTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName);
    }


    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask1 = new MyTimerTask("任务1");
        timer.schedule(timerTask1, 2000L, 1000L);
    }

}
