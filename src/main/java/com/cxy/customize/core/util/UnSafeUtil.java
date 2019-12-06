package com.cxy.customize.core.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeUtil {
    private UnSafeUtil() {
    }

    //get an Unsafe object

    /**
     * 获取Unsafe的单例对象,反射
     * @return
     */
    public static Unsafe getUnasfe(){
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);
        } catch (Exception e) {
            System.out.println(StrUtil.format(e.getMessage(),e));
           return null;
        }
    }


    //memory start
    /**
     * 在Java中创建的对象都处于堆内内存（heap）中，
     * 堆内内存是由JVM所管控的Java进程内存，并且它们遵循JVM的内存管理机制，JVM会采用垃圾回收机制统一管理堆内存。
     * 与之相对的是堆外内存，存在于JVM管控之外的内存区域，Java中对堆外内存的操作，依赖于Unsafe提供的操作堆外内存的native方法。
     */
    //memory end
}
