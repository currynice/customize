package com.cxy.customize.core.proxy.test;

import java.lang.reflect.Proxy;

/**
 * 为指定的接口在系统运行期间动态地生成代理对象，从而帮助我们走出最初使用静态代理实现AOP的窘境
 *
 * 动态代理对象的创建原理:
 * 假设创建的代理对象名为 $Proxy0：
 *
 * 根据传入的interfaces动态生成一个类，实现interfaces中的接口
 * 通过传入的classloder将刚生成的类加载到jvm中。即将$Proxy0类load
 * 调用$Proxy0的$Proxy0(InvocationHandler)构造函数 创建$Proxy0的对象，并且用interfaces参数遍历其所有接口的方法，并生成实现方法，这些实现方法的实现本质上是通过反射调用被代理对象的方法。
 * 将$Proxy0的实例返回给客户端。
 * 当调用代理类的相应方法时，相当于调用 InvocationHandler.invoke(Object, Method, Object []) 方法
 *
 * https://www.hutool.cn/docs/#/aop/%E5%88%87%E9%9D%A2%E4%BB%A3%E7%90%86%E5%B7%A5%E5%85%B7-ProxyUtil
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
