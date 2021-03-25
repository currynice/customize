package com.cxy.soft_design.principle.OCP.good;

public interface MessageSender {
    void send(NotificationEmergencyLevel level, String cellphone, String message);
}
