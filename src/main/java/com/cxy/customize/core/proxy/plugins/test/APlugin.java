package com.cxy.customize.core.proxy.plugins.test;

import com.cxy.customize.core.proxy.interceptors.JDKPluginInterceptors;
import com.cxy.customize.core.proxy.plugins.Invocation;
import com.cxy.customize.core.proxy.plugins.Plugin;

public class APlugin implements Plugin {


        /** 执行拦截逻辑的方法 */
        @Override
        public Object intercept(Invocation invocation) {
            try {
                //执行结果前后加上  "pluginA"
                return "pluginA " + invocation.proceed() + " pluginA";
            } catch (Exception e) {
                return null;
            }
        }

        /** 为原先的类生成代理对象 */
        @Override
        public Object plugin(Object target) {
            return JDKPluginInterceptors.wrap(target, this);
        }
    }
