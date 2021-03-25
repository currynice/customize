package com.cxy.soft_design.principle.DI;


//短信
public class SmsMessageSender implements MessageSender {
    @Override
    public void send(String cellphone, String message) {
        System.out.println("短信"+cellphone+":"+message);
    }
}
