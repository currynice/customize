package com.cxy.customize.core.proxy.interceptors;

import com.cxy.customize.core.proxy.plugins.Invocation;
import com.cxy.customize.core.proxy.plugins.Plugin;
import com.cxy.customize.core.proxy.plugins.test.IGetStr;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk方式生成代理对象->插件
 */
public class JDKPluginInterceptors implements InvocationHandler {

        /** 目标对象 */
        private final Object target;
        /** Interceptor对象 */
        private final Plugin interceptor;

        public JDKPluginInterceptors(Object target, Plugin interceptor) {
            this.target = target;
            this.interceptor = interceptor;
        }

        /** 生成代理对象 */
        public static Object wrap(Object target, Plugin interceptor) {
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    new Class[]{IGetStr.class},
                    new JDKPluginInterceptors(target, interceptor));
        }

        /** 被代理对象的方法执行时，这个方法会被执行 */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 只为方法getStrZero生成代理对象
            if (method.getName().equals("getStrZero")) {
                return interceptor.intercept(new Invocation(target, method, args));
            }
            return method.invoke(target, args);
        }

}
