package com.cxy.soft_design.principle.OCP.bad;



/**
 * Description: 告警处理类  </br>
 * Date: 2021/3/24 11:05
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class Alert {
    //告警规则
    private AlertRule rule;

    //通知
    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    /**
     * 当时间段内 接口的 TPS 超过某个预先设置的最大值时，
     *   或当接口请求出错数大于某个最大允许值时，触发告警(通知接口的相关负责人或者团队)
     * @param api           接口
     * @param requestCount 接口的 TPS
     * @param errorCount   接口请求出错数
     * @param durationOfSeconds  时间段内(s)
     */
    //// 改动一：添加参数timeoutCount (超时次数)
    public void check(String api, long requestCount, long errorCount,long timeoutCount, long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.sendMessage(NotificationEmergencyLevel.URGENCY, "...","接口的 TPS 超过阈值");
        }
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.sendMessage(NotificationEmergencyLevel.SEVERE, "...","接口请求出错数超过阈值");
        }

        //改动二：添加接口超时处理逻辑
        long timeoutTps = timeoutCount / durationOfSeconds;
        if (timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()) {
            notification.sendMessage(NotificationEmergencyLevel.URGENCY, "....","...");
        }
    }
}
