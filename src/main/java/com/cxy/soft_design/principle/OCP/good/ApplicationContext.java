package com.cxy.soft_design.principle.OCP.good;

import com.cxy.soft_design.principle.OCP.good.handlers.ErrorAlertHandler;
import com.cxy.soft_design.principle.OCP.good.handlers.TimeoutAlertHandler;
import com.cxy.soft_design.principle.OCP.good.handlers.TpsAlertHandler;

/**
 * Description:   </br>
 * Date: 2021/3/25 23:15
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class ApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;

    public void initializeBeans() {
        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
        notification = new Notification(new SmsMessageSender());
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));

    }
    public Alert getAlert() { return alert; }

    // 饿汉式单例
    private static final ApplicationContext instance = new ApplicationContext();
    private ApplicationContext() {
        initializeBeans();
    }
    public static ApplicationContext getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略设置apiStatInfo数据值的代码
        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}


