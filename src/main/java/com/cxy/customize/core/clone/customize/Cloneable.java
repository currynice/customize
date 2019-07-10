package com.cxy.customize.core.clone.customize;

/**
 * 添加泛型后，既不会忘了 重写clone方法,也不需要强制转型了
 * @param <T>
 */
public interface Cloneable<T> extends java.lang.Cloneable {

    /**
     * 克隆当前对象，浅复制
     * @return 克隆后的对象
     */
    T clone();
}
