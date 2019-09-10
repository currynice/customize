package com.cxy.customize.core.lang.copier;


import com.cxy.customize.core.lang.Filter;

/**
 * 抽象对象之间的拷贝
 * @param <T>
 * @param <C>
 */
public abstract class CopierTool<T,C extends CopierTool<T,C>> implements Copier<T> {

    /** 源 */
    protected T src;
    /** 目标 */
    protected T dest;
    /** 拷贝过滤器，可以过滤掉不需要拷贝的源 */
    protected Filter<T> copyFilter;


    public T getSrc() {
        return src;
    }


    @SuppressWarnings("unchecked")
    public C setSrc(T src) {
        this.src = src;
        return (C)this;
    }

    public T getDest() {
        return dest;
    }

    @SuppressWarnings("unchecked")
    public C setDest(T dest) {
        this.dest = dest;
        return (C)this;
    }

    public Filter<T> getCopyFilter() {
        return copyFilter;
    }

    @SuppressWarnings("unchecked")
    public C setCopyFilter(Filter<T> copyFilter) {
        this.copyFilter = copyFilter;
        return (C)this;
    }
}
