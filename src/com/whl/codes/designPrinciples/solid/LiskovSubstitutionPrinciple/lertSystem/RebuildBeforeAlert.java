package com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem;

import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.alertHandler.AlertRule;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.NotificationEmergencyLevel;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.fromApiSystem.Api;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.Notification;

/**
 * @author whl
 * @version V1.0
 * @Title: 重构之前的Alert
 * @Description:
 */
public class RebuildBeforeAlert {
    public class Alert {
        private AlertRule rule;
        private Notification notification;

        public Alert(AlertRule rule, Notification notification) {
            this.rule = rule;
            this.notification = notification;
        }

        public void check(Api api, long requestCount, long errorCount, long durationOfSeconds, long timeoutCount) {
            long tps = requestCount / durationOfSeconds;//每秒接收的请求数
            long timeoutTps = timeoutCount/durationOfSeconds;//每秒请求超时的请求数
            //当tps值大于api接口规定的最大tps值, 告警
            if (tps > rule.getMaxTps(api)) {
                notification.notify(NotificationEmergencyLevel.URGENCY, "...");
            }
            //当接口请求出错数大于api接口规定的最大允许出错数, 告警
            if (errorCount > rule.getMaxErrorCount(api)) {
                notification.notify(NotificationEmergencyLevel.SEVERE, "...");
            }
            //当timeoutTps大于api接口规定的最大timeoutTps值, 告警
            if (timeoutTps > rule.getMaxTimeoutTps(api)) {
                notification.notify(NotificationEmergencyLevel.URGENCY, "...");
            }
        }
    }
}
