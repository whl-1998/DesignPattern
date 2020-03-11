package com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler;

import com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem.ApiStatInfo;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.notification.Notification;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.notification.NotificationEmergencyLevel;

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
