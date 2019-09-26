package com.cxy.customize.core.proxy.interceptors;



import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.proxy.aspects.Aspect;
import com.cxy.customize.core.util.ReflectUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * JDK 方式生成代理对象 -> aop
 */
public class JDKAopInterceptors implements InvocationHandler {
    private Object target;
    private Aspect aspect;


    /**
     *
     * @param target   被代理的对象
     * @param aspect  切面接口
     */
    public JDKAopInterceptors(Object target, Aspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }


    public Object getTarget() {
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object target = this.target;
        final Aspect aspect = this.aspect;
        Object result = null;
        //方法前执行成功
        if (aspect.before(target, method, args)) {
            try {
                //区分了static和普通方法，修改了accessible
                result = ReflectUtil.invoke(target, method, args);
            } catch (UtilException e) {
                final Throwable cause = e.getCause();
                if (e.getCause() instanceof InvocationTargetException) {
                    aspect.afterThrowing(target, method, args, ((InvocationTargetException) cause).getTargetException());
                } else {
                    throw e;// 其它异常属于代理的异常，直接抛出
                }
            }
        }
        if (aspect.after(target, method, args)) {
            return result;
        }
        return null;
    }
}
