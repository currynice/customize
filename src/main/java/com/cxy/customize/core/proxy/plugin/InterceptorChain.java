package com.cxy.customize.core.proxy.plugin;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChain {
    private  List<Interceptor> chain = new ArrayList<>();

    public Object permitAll(Object target){
        for(Interceptor interceptor :chain){
            target = interceptor.plugin(target);
        }
        return target;
    }

    public  List<Interceptor> addInterceptor(Interceptor interceptor) {
       chain.add(interceptor);
       return chain;
    }
}
