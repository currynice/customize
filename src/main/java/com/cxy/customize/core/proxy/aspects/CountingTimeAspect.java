package com.cxy.customize.core.proxy.aspects;


import cn.hutool.core.date.TimeInterval;
import com.cxy.customize.core.util.StrUtil;
import java.lang.reflect.Method;

/**
 * 计算方法执行时间的方法
 */
public class CountingTimeAspect extends DefaultAspectImpl {
    //毫秒级别够了
    private TimeInterval timeInterval = new TimeInterval();

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        timeInterval.start();
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args) {
        System.out.println(StrUtil.format("方法{}.{}执行了{}ms",target.getClass().getName(),method.getName(),timeInterval.intervalMs()));
        return true;
    }
}
