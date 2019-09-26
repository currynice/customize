package com.cxy.customize.core.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestHandler implements InvocationHandler {
    //代理对象
    private Object target;

    public TestHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("say".equals(method.getName())){
            return method.invoke(target,args);
        }
        return null;
    }
}
