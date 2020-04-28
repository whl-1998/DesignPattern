package com.whl.codes.designPatterns.structural.proxy;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class RequestInfo {
    private String apiName;
    private long startTimestamp;
    private long responseTime;

    public RequestInfo(String apiName, long startTimestamp, long responseTime) {
        this.apiName = apiName;
        this.startTimestamp = startTimestamp;
        this.responseTime = responseTime;
    }
}
