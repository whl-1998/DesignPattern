package com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler;

import com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem.Api;

/**
 * @author whl
 * @version V1.0
 * @Title: 模拟告警规则
 * @Description: 不同的接口有不同的rule, 因此通过传递Api实例来获取不同类型的值
 */
public class AlertRule {
    public long getMaxErrorCount(Api api) {
        //TODO: 获取当前接口"允许请求出错"的最大值
        return 0;
    }

    public long getMaxTps(Api api) {
        //TODO: 获取当前接口"每秒钟能够处理的接口请求"的最大值
        return 0;
    }

    public long getMaxTimeoutTps(Api api) {
        //TODO: 获取当前接口"每秒钟允许接口请求超时"的最大值
        return 0;
    }
}
