package com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler;

import com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem.ApiStatInfo;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.notification.Notification;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.notification.NotificationEmergencyLevel;

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
