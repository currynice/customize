package com.cxy.soft_design.principle.OCP.good;

/**
 * Description:   </br>
 * Date: 2021/3/25 23:07
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ApiStatInfo {
    private String api;
    private long requestCount;
    private long errorCount;
    private long durationOfSeconds;

    //此处新增
    private long timeoutCount;


    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getDurationOfSeconds() {
        return durationOfSeconds;
    }

    public void setDurationOfSeconds(long durationOfSeconds) {
        this.durationOfSeconds = durationOfSeconds;
    }

    public long getTimeoutCount() {
        return timeoutCount;
    }

    public void setTimeoutCount(long timeoutCount) {
        this.timeoutCount = timeoutCount;
    }
}