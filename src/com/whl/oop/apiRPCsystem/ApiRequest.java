package com.whl.oop.apiRPCsystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 模拟接口请求
 * @Description:
 */
public class ApiRequest {
    private String appId;
    private String token;
    private String originalUrl;
    private long timeStamp;

    /**
     * 根据url构建ApiRequest对象
     * @param url
     * @return
     */
    public ApiRequest(String url) {
        String[] str1 = url.split("[?]");
        this.originalUrl = str1[0];
        String[] str2 = str1[1].split("[&]");
        for (int i = 0; i < str2.length; i++) {
            String[] str3 = str2[i].split("[=]");
            if (str3[0].equals("appId")) {
                this.appId = str3[1];
            } else if (str3[0].equals("token")) {
                this.token = str3[1];
            } else if (str3[0].equals("ts")) {
                this.timeStamp = Long.valueOf(str3[1]);
            }
        }
    }

    public String getAppId() {
        return appId;
    }

    public String getToken() {
        return token;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
