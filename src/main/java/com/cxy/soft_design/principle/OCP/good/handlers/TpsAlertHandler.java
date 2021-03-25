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
public class TpsAlertHandler extends AlertHandler {


    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/ apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.sendMessage(NotificationEmergencyLevel.URGENCY, "...","接口的 TPS 超过阈值");
        }
    }

}
