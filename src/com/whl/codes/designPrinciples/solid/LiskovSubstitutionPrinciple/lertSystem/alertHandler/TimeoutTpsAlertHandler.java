package com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.alertHandler;

import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.NotificationEmergencyLevel;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.fromApiSystem.ApiStatInfo;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.Notification;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class TimeoutTpsAlertHandler extends AlertHandler{
    public TimeoutTpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long timeoutTps = apiStatInfo.getTimeoutCount() / apiStatInfo.getDurationOfSeconds();
        if (timeoutTps > rule.getMaxTimeoutTps(apiStatInfo.getApi())) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "当前接口请求超时数超过阈值");
        }
    }
}
