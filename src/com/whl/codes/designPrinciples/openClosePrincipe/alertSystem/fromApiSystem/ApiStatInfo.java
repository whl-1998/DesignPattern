package com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 封装check()方法的入参, 调用者将参数封装在ApiStatInfo中调用check(ApiStatInfo a)方法
 * @Description:
 */
public class ApiStatInfo {
    private Api api;
    private long requestCount;
    private long durationOfSeconds;
    private long errorCount;
    private long timeoutCount;

    public long getRequestCount() {
        return requestCount;
    }

    public long getDurationOfSeconds() {
        return durationOfSeconds;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public long getTimeoutCount() {
        return timeoutCount;
    }

    public Api getApi() {
        return api;
    }
}
