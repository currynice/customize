package com.cxy.customize.core.util;

import com.cxy.customize.core.exceptions.UtilException;

import java.lang.reflect.Field;

public class ReflectUtil {




    /**
     * 获得对象的字段值
     * @param obj
     * @param field
     * @return
     */
    public static Object getFieldValue(Object obj,Field field){
       if(null==obj || field==null){
           return null;
       }
       //针对private static
       field.setAccessible(true);
       Object fieldValue = null;
       try{
           fieldValue = field.get(obj);
       }catch (IllegalAccessException e){
           throw new UtilException(StrUtil.format("IllegalAccess for {}.{}", obj.getClass(), field.getName()),e);
       }
       return fieldValue;
    }

}
