package com.cxy.soft_design.principle.OCP.good.handlers;

import com.cxy.soft_design.principle.OCP.good.AlertRule;
import com.cxy.soft_design.principle.OCP.good.ApiStatInfo;
import com.cxy.soft_design.principle.OCP.good.Notification;

/**
 * Description:   </br>
 * Date: 2021/3/25 23:09
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public abstract class AlertHandler {
    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
