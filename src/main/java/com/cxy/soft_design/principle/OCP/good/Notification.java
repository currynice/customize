package com.cxy.soft_design.principle.OCP.good;


//通知service
public class Notification {
    private MessageSender messageSender;

    // 通过构造函数将messageSender传递进来
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }


    /**
     * 进行
     * @param level
     * @param cellphone
     * @param message
     */
    public void sendMessage(NotificationEmergencyLevel level, String cellphone, String message) {
        this.messageSender.send(level,cellphone, message);
    }

}
