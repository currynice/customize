package com.cxy.customize.core.clone.customize;


/**
 * 前提:继承该类完成浅克隆,使用类不能继承其他类,这时候可以实现Cloneable接口
 */
public class CloneSupport<T> implements Cloneable<T>{

    @Override
    public T clone(){
        try{
            return (T)super.clone();
        }catch (CloneNotSupportedException e){
            throw new CloneRuntimeException(e);
        }
    }
}
