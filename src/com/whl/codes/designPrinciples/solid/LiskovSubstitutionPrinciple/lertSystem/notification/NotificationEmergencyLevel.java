package com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.notification;

public enum NotificationEmergencyLevel {
    SEVERE("严重"), URGENCY("紧急"), NORMAL("正常"), TRIVIAL("无关紧要");

    String info;

    NotificationEmergencyLevel(String info) {
        this.info = info;
    }
}
