package com.cxy.customize.core.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 注解工具类
 * https://hutool.cn/docs/#/core/%E6%B3%A8%E8%A7%A3/%E6%B3%A8%E8%A7%A3%E5%B7%A5%E5%85%B7-AnnotationUtil?id=%E6%B3%A8%E8%A7%A3%E5%B7%A5%E5%85%B7-annotationutil
 */
public class AnnotationUtil {

    /** todo
     * 动态修改注解的值
     * @param targetClass 修改的类
     * @param fieldName   修改的字段
     * @param annotationClass 字段上的注解
     * @param annotationKey   修改的key
     * @param value   修改的值
     */
    public static void dynamicallyEditValue(Class<?> targetClass,String fieldName,Class annotationClass,String annotationKey,Object value) throws  NoSuchFieldException,IllegalAccessException {
        try{
            //获取字段
            Field field = targetClass.getDeclaredField(fieldName);
            //获取注解实例
            Object annotation = field.getAnnotation(annotationClass);

            InvocationHandler annotationInvocationHandler = Proxy.getInvocationHandler(annotation);
            Field hfield = annotationInvocationHandler.getClass().getDeclaredField("memberValues");
            // 因为这个字段为 private final 修饰，所以要打开权限
            hfield.setAccessible(true);
            Map<String, Object> memberValues =( Map<String, Object>)hfield.get(annotationInvocationHandler);
            memberValues.put(annotationKey,value);
        }catch (NoSuchFieldException e){
             throw new NoSuchFieldException("没有该字段");
        }catch (IllegalAccessException e){
            throw new IllegalAccessException("字段访问失败");
        }

    }
}
