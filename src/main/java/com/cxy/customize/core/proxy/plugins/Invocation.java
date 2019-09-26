package com.cxy.customize.core.proxy.plugins;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 简单的封装了一下目标对象，目标方法和目标方法的参数。proceed方法就是执行目标对象的目标方法
 */
public class Invocation {

    private final Object target;
    private final Method method;
    private final Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }


    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}