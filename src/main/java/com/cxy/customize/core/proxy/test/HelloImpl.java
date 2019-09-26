package com.cxy.customize.core.proxy.test;



public class HelloImpl implements ISay {
    @Override
    public void say(String t) {
        System.out.println("hello"+t);
    }
}
