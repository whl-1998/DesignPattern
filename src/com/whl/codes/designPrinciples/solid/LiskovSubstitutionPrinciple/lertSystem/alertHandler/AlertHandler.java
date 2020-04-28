package com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.alertHandler;

import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.fromApiSystem.ApiStatInfo;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification.Notification;

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
