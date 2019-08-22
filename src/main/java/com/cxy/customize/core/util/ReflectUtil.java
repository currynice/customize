package com.cxy.customize.core.util;

import cn.hutool.core.lang.SimpleCache;
import com.cxy.customize.core.exceptions.UtilException;

import java.lang.reflect.Field;


/**
 * 反射Util
 */
public class ReflectUtil {

    /** 字段缓存 todo 实现自己的缓存池*/
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();

    private ReflectUtil(){

    }

    /**
     * 获得对象的指定field值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj,String fieldName)throws UtilException{
        if(null==obj||StrUtil.isBlank(fieldName)){
            return null;
        }
        Field field = getField(obj.getClass(),fieldName);
        return  getFieldValue(obj,field);
    }

    /**
     * 获得对象的字段值
     * @param obj
     * @param field
     * @return
     */
    public static Object getFieldValue(Object obj,Field field)throws UtilException{
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

    /**
     *
     *   获得某个类的 <strong>所有字段</strong>：是下面两个jdk方法的并集
     *  获得某个类的所有的公共（public）的字段，包括父类中的字段 {@link Class#getFields()}
     *  获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段{@link Class#getDeclaredFields()}
     * @param typeClass  注意非null
     * @param fieldName
     * @return
     * @throws UtilException
     */
    public static Field getField(Class typeClass,String fieldName){
           final Field[] allfields = getAllField(typeClass);
        if (ArrayUtil.isNotEmpty(allfields)) {
            for (Field field : allfields) {
                if ((fieldName.equals(field.getName()))) {
                    return field;
                }
            }
        }
        return null;
    }

//todo
    public static Field[]  getAllField(Class typeClass){
return null;
    }




}
