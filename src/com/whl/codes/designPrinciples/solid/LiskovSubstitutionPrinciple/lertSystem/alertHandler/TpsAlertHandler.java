package com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.alertHandler;

import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.NotificationEmergencyLevel;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.fromApiSystem.ApiStatInfo;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.Notification;

/**
 * @author whl
 * @version V1.0
 * @Title: 当tps超过阈值, 触发告警
 * @Description:
 */
public class TpsAlertHandler extends AlertHandler {
    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMaxTps(apiStatInfo.getApi())) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "当前接口tps超过阈值");
        }
    }
}
