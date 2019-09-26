package com.cxy.customize.core.proxy.aspects;



import java.lang.reflect.Method;

/**
 * 实现了{@link Aspect}自定义切面接口的方法，全部返回true,没有具体实现
 * 用途:可以继承全部或部分方法
 */
public class DefaultAspectImpl implements Aspect {


    @Override
    public boolean before(Object target, Method method, Object[] args) {
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args) {
        return true;
    }

    @Override
    public boolean afterThrowing(Object target, Method method, Object[] args, Throwable e) {
        return true;
    }
}
