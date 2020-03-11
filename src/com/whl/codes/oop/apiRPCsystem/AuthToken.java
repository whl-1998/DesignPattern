package com.whl.oop.apiRPCsystem;

/**
 * @author whl
 * @version V1.0
 * @Title: token
 * @Description:
 */
public class AuthToken {
    private String token;
    private long createTime;
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long timeStamp) {
        this.token = token;
        this.createTime = timeStamp;
    }

    public static AuthToken generate(String originalUrl, String appId, String password, long timeStamp) {
        StringBuilder sb = new StringBuilder();
        String temp = sb.append(originalUrl).append(appId).append(password).append(timeStamp).toString();
        return encrypt(temp);
    }

    public boolean isExpired() {
        //判断token创建的时间戳距离当前时间戳是否大于expiredTimeInterval
        return System.currentTimeMillis() <= createTime + expiredTimeInterval;
    }

    public boolean match(AuthToken clientAuthToken) {
        //TODO: 校验两个token是否匹配
        return false;
    }

    private static AuthToken encrypt(String str) {
        //TODO: 对str进行加密, 返回一个TOKEN对象
        return null;
    }
}
