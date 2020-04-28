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
public class ErrorAlertHandler extends AlertHandler{
    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMaxErrorCount(apiStatInfo.getApi())) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "当前接口请求出错数超过阈值");
        }
    }
}
