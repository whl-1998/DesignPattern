package com.whl.codes.designPrinciples.solid.DependencyInversionPrinciple.iocAndDi.notifySystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 通知类
 * @Description:
 */
public class Notification {
    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }
}
