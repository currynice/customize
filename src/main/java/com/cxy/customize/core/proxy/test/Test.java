package com.cxy.customize.core.proxy.test;

import java.lang.reflect.Proxy;

/**
 * 为指定的接口在系统运行期间动态地生成代理对象，从而帮助我们走出最初使用静态代理实现AOP的窘境
 */
public class Test {
    public static void main(String args[]){

        /**
         * 提供lassloader,生成代理类
         * 代理类应该实现的接口
         * 实现InvocationHandler的切面类
         */
        ISay sayHello = (ISay)Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(),new TestHandler( new HelloImpl()));
        sayHello.say("22");

        ISay sayBye = (ISay)Proxy.newProxyInstance(ByeImpl.class.getClassLoader(), ByeImpl.class.getInterfaces(),new TestHandler( new ByeImpl()));
        sayBye.say("11");
    }
}
