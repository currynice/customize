package com.cxy.customize.thread;

public class AsyncMethod {
    public static String methodWithError(){
        throw new IllegalArgumentException("参数不对");
    }

}
