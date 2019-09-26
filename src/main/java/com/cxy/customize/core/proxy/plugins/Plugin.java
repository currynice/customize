package com.cxy.customize.core.proxy.plugins;

/**
 * 自定义插件拦截器
 */
public interface Plugin {
    //执行拦截逻辑
    Object intercept(Invocation invocation);


    /**
     * 生成代理对象并返回
     * @param target 被拦截对象
     * @return
     */
    Object plugin(Object target);
}
