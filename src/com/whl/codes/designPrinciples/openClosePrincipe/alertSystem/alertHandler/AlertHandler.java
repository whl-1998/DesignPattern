package com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler;

import com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem.ApiStatInfo;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.notification.Notification;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
