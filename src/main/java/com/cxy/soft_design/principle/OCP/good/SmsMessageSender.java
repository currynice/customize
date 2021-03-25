package com.cxy.soft_design.principle.OCP.good;


//短信
public class SmsMessageSender implements MessageSender {
    @Override
    public void send(NotificationEmergencyLevel level, String cellphone, String message) {
        System.out.println("发短信给"+cellphone+":"+message+level+"级别");
    }
}
