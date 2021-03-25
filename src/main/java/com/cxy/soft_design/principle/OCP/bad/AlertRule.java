package com.cxy.soft_design.principle.OCP.bad;

/**
 * Description: 各项指标阈值  </br>
 * Date: 2021/3/24 11:06
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class AlertRule {

    //即maxRequestCount
    private int maxTps;

    private int maxErrorCount;

    private int maxTimeoutTps;

    public AlertRule getMatchedRule(String api) {
        AlertRule ruleDemo= new AlertRule();
        ruleDemo.setMaxTps(100);
        ruleDemo.setMaxErrorCount(10);
        return ruleDemo;
    }

    public int getMaxTps() {
        return maxTps;
    }

    public void setMaxTps(int maxTps) {
        this.maxTps = maxTps;
    }

    public int getMaxErrorCount() {
        return maxErrorCount;
    }

    public void setMaxErrorCount(int maxErrorCount) {
        this.maxErrorCount = maxErrorCount;
    }

    public int getMaxTimeoutTps() {
        return maxTimeoutTps;
    }

    public void setMaxTimeoutTps(int maxTimeoutTps) {
        this.maxTimeoutTps = maxTimeoutTps;
    }
}
