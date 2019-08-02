package com.cxy.customize.core.util;

import com.cxy.customize.core.lang.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 枚举工具类
 *
 * @author cxy
 */
public class EnumUtil {

    /**
     * 是不是枚举类
     * @param clazz
     * @return
     */
    public static boolean isEnum(Class clazz){
        Assert.notNull(clazz);
        return clazz.isEnum();
    }

    /**
     * 对象是不是枚举类
     * @param obj
     * @return
     */
    public static boolean isEnum(Object obj){
        Assert.notNull(obj);
        return obj.getClass().isEnum();
    }


    /**
     * Enum 对象转String,
     * {@link Enum#name()}方法
     * @param e
     * @return
     */
    public static String toString(Enum e){
        return null != e ? e.name() : null;
    }

    /**
     * 获得Enum 的值(列表形式)
     * @param clazz
     * @return
     */
    public static List<String> getNames(Class<? extends Enum> clazz){
        Enum<?>[] enums =  clazz.getEnumConstants();
        if(ArrayUtil.isEmpty(enums)){
            return null;
        }
        final List<String> names = new ArrayList<>(enums.length);
        for( Enum<?> e:enums){
             names.add(e.name());
        }
        return names;
    }

    /**
     * 获得指定 field 的值(列表形式)
     * @param clazz   todo 封装，减少异常抛出
     * @param fieldName  最终调用getXXX方法
     * @return
     */
    public static List<Object> getFieldNames(Class<? extends Enum> clazz,String fieldName)throws NoSuchFieldException{
        //枚举对象数组
        Enum<?>[] enums =  clazz.getEnumConstants();
        if(ArrayUtil.isEmpty(enums)){
            return null;
        }
        final List<Object> fieldNames = new ArrayList<>(enums.length);
        for( Enum<?> e:enums){
            fieldNames.add(ReflectUtil.getFieldValue(e,fieldName));
        }
        return fieldNames;
    }




}
