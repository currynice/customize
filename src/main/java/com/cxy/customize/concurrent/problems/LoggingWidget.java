package com.cxy.customize.concurrent.problems;

public class LoggingWidget extends Widget{

    @Override
    public synchronized void doSth() {
        System.out.println("calling");
        super.doSth();
    }
}
