package com.cxy.customize.core.lang.copier;


/**
 * 拷贝接口
 */
public interface Copier<T> {

    /**
     * 完成拷贝
     * @return 拷贝的目标
     */
  T copy();
}
