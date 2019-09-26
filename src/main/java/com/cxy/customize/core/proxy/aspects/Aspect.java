package com.cxy.customize.core.proxy.aspects;

import java.lang.reflect.Method;

/**
 * 切面接口，简单的前置before，后置after以及异常afterThrow,没了...
 */
public interface Aspect {
    /**
     * 目标方法执行之前的操作
     * @param target,method,args (目标对象,方法，参数的封装)
     * @return true继续执行下去
     */
    boolean before(Object target, Method method, Object[] args);


    /**
     * 目标方法执行之后的操作
     * @param target,method,args (目标对象,方法，参数的封装)
     * @return true继续执行下去
     */
    boolean after(Object target, Method method, Object[] args);


    /**
     * 目标方法执行之前的操作
     * @param target,method,args.e (目标对象,方法，参数,异常的封装)
     * @return true继续执行下去
     */
    boolean afterThrowing(Object target, Method method, Object[] args, Throwable e);
}
