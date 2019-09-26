package com.cxy.customize.core.proxy.plugins;

import java.util.ArrayList;
import java.util.List;

public class PluginChain {
    private  List<Plugin> chain = new ArrayList<>();

    public Object permitAll(Object target){
        for(Plugin interceptor :chain){
            target = interceptor.plugin(target);
        }
        return target;
    }

    public  List<Plugin> addInterceptor(Plugin interceptor) {
       chain.add(interceptor);
       return chain;
    }
}
