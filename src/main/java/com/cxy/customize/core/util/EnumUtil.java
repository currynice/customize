package com.cxy.customize.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 枚举工具类
 *
 * @author cxy
 */
public class EnumUtil {


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
     * 获得指定 fieldName 的值(列表形式)
     * @param clazz   todo 封装，减少异常抛出
     * @param fieldName
     * @return
     */
    public static List<String> getFieldNames(Class<? extends Enum> clazz,String fieldName)throws NoSuchFieldException{
        Enum<?>[] enums =  clazz.getEnumConstants();
        if(ArrayUtil.isEmpty(enums)){
            return null;
        }
        final List<String> fieldNames = new ArrayList<>(enums.length);
        for( Enum<?> e:enums){
            Field field = clazz.getDeclaredField(fieldName);
            fieldNames.add(StrUtil.utf8Str(ReflectUtil.getFieldValue(e,field)));
        }
        return fieldNames;
    }




}
