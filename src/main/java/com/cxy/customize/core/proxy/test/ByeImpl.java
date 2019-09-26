package com.cxy.customize.core.proxy.test;



public class ByeImpl implements ISay {
    @Override
    public void say(String t) {
        System.out.println("bye"+t);
    }
}
