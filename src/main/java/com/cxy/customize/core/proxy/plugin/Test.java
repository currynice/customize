package com.cxy.customize.core.proxy.plugin;

public class Test {
    public static void main(String args[]){
        InterceptorChain chain  = new InterceptorChain();
        chain.addInterceptor(new APlugin());
        chain.addInterceptor(new BPlugin());

        IGetStr iGetStr = new GetStrImpl();
        iGetStr = (IGetStr)chain.permitAll(iGetStr);

        System.out.println(iGetStr.getStrZero());

        System.out.println(iGetStr.getStrOne());
    }

}
