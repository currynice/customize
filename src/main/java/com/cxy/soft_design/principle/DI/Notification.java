package com.cxy.soft_design.principle.DI;



public class Notification {

    private MessageSender messageSender;

    // 通过构造函数将messageSender传递进来
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }

    public static void main(String args[]){
        //使用Notification
        MessageSender messageSender = new SmsMessageSender();
        Notification notification = new Notification(messageSender);

        notification.sendMessage("13906515639","你好");
    }
}
