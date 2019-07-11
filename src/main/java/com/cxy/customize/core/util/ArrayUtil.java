package com.cxy.customize.core.util;



import com.cxy.customize.core.exceptions.UtilException;

import java.lang.reflect.Array;

/**
 * 数组
 */
public class ArrayUtil {
    /** 数组中元素未找到的下标，值为-1 */
    public static final int INDEX_NOT_FOUND = -1;

    // ---------------------------------------------------------------------- isEmpty
    /**
     * 数组是否为空
     * @param <T> 数组元素类型
     * @param array 数组
     * @return 是否为空
     */
    public static<T> boolean isEmpty(T...array){
        return array==null||array.length==0;
    }

    /**
     * 数组是否为空<br>
     * 此方法会匹配单一对象，如果此对象为{@code null}则返回true<br>
     * 如果此对象为非数组，理解为此对象为数组的第一个元素，则返回false<br>
     * 如果此对象为数组对象，数组长度大于0情况下返回false，否则返回true
     *
     * @param array 数组
     * @return 是否为空
     */
//    public static boolean isEmpty(Object array) {
//        if (null == array) {
//            return true;
//        } else if (isArray(array)) {
//            return 0 == Array.getLength(array);
//        }
//        throw new UtilException("Object to provide is not a Array !");
//    }


    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final long... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final int... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final short... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final char... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final byte... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final double... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final float... array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final boolean... array) {
        return array == null || array.length == 0;
    }

    // ---------------------------------------------------------------------- isNotEmpty
    /**
     * 数组是否为非空
     *
     * @param <T> 数组元素类型
     * @param array 数组
     * @return 是否为非空
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean isNotEmpty(final T... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空<br>
     * 此方法会匹配单一对象，如果此对象为{@code null}则返回false<br>
     * 如果此对象为非数组，理解为此对象为数组的第一个元素，则返回true<br>
     * 如果此对象为数组对象，数组长度大于0情况下返回true，否则返回false
     *
     * @param array 数组
     * @return 是否为非空
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(final Object array) {
        return !isEmpty((Object) array);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final long... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final int... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final short... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final char... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final byte... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final double... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final float... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(final boolean... array) {
        return (array != null && array.length != 0);
    }

    /**
     * 数组是否含有 Null元素
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static<T> boolean hasNull(T...array){
        if(isNotEmpty(array)){
            for(T element:array){
                if(null==element){
                   return  true;
                }
            }
        }
        return false;
    }

    /**
     * 返回数组中第一个非 Null元素
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T firstNonNull(T...array){
        if(isNotEmpty(array)){
            for(final T element:array){
                if(null != element){
                    return  element;
                }
            }
        }
        return null;
    }

    /**
     * 新建一个空数组
     * @param <T> 数组元素类型
     * @param componentType 元素类型
     * @param newSize 大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize){
        return (T[])Array.newInstance(componentType,newSize);
    }

    /**
     * 新建一个空数组
     * @param newSize 大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static Object[] newArray(int newSize){
        return new Object[newSize];
    }

    /**
     * 获取数组对象的元素类型
     *
     * @param array 数组对象
     * @return 元素类型
     */
    public static Class<?> getComponentType(Object array){
        return null==array?null:array.getClass().getComponentType();
    }


    /**
     * 获取数组对象的元素类型
     *
     * @param arrayClass 数组类
     * @return 元素类型
     */
    public static Class<?> getComponentType(Class<?> arrayClass){
        return null==arrayClass?null:arrayClass.getComponentType();
    }

    /**
     * 根据数组元素类型，获取数组的类型<br>
     * 方法是通过创建一个空数组从而获取其类型
     * @param componentType
     * @return
     */
    public static Class<?> getArrayType(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }

    /**
     *
     * @param type
     * @param arrayObj
     * @return
     * @throws NullPointerException 待转换数组为空
     * @throws IllegalArgumentException arrayObj不是数组
     */
    public static Object[] cast(Class<?> type, Object arrayObj)throws NullPointerException, IllegalArgumentException{
            if(null==arrayObj){
                throw  new NullPointerException("参数arrayObj为空");
            }
            if(!arrayObj.getClass().isArray()){
                throw  new IllegalArgumentException("arrayObj不是数组");
            }
            if (null == type) {
                return (Object[]) arrayObj;
            }
        Class<?>  componentType = type.isArray()?type.getComponentType():type;
        final Object[] array = (Object[]) arrayObj;
        final Object[] result = ArrayUtil.newArray(componentType, array.length);
        System.arraycopy(array,0,result,0,array.length);
        return result;
    }



}
