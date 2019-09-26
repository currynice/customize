package com.cxy.customize.core.proxy.plugins.test.aop;

public class Animal {

    public void eat(){
        int b=0;
        for(int i=0;i<1000;i++){
            b+=i;
        }
        System.out.println(b);
    }

}
