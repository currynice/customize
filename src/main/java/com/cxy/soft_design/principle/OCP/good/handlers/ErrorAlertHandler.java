package com.cxy.soft_design.principle.OCP.good.handlers;

import com.cxy.soft_design.principle.OCP.good.NotificationEmergencyLevel;
import com.cxy.soft_design.principle.OCP.good.AlertRule;
import com.cxy.soft_design.principle.OCP.good.ApiStatInfo;
import com.cxy.soft_design.principle.OCP.good.Notification;

/**
 * Description:   </br>
 * Date: 2021/3/25 23:11
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ErrorAlertHandler extends AlertHandler {


    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.sendMessage(NotificationEmergencyLevel.SEVERE, "...","接口请求出错数超过阈值");
        }
    }

}
