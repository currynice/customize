package com.cxy.customize.core.util;


import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.lang.Assert;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * 数组工具类
 */
public class ArrayUtil {
    private ArrayUtil() {
    }

    /** 数组中元素未找到的下标，值为-1 */
    public static final int INDEX_NOT_FOUND = -1;

    // ---------------------- 判空 -------------- isEmpty ---------------------
    /**
     * 数组是否为Empty
     * @param <T> 数组元素类型
     * @param array 数组
     * @return 是否为空
     */
    public static<T> boolean isEmpty(T...array){
        return array==null||array.length==0;
    }

    /**
     * 数组对象是否为空<br>
     * 此方法会匹配单一对象，如果此对象为{@code null}则返回true<br>
     * 如果此对象为非数组，理解为此对象为数组的第一个元素，则返回false<br>
     * 如果此对象为数组对象，数组长度大于0情况下返回false，否则返回true
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object array) {
        if (null == array) {
            return true;
        } else if (isArray(array)) {
            return 0 == Array.getLength(array);
        }
        throw new UtilException("Object to provide is not a Array !");
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
     *
     * @param array 数组
     * @return 是否为非空
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(final Object array) {
        return !isEmpty((Object) array);
    }


//-------------------hasNull------
    /**
     * 数组是否含有 为null的元素
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
     *
     * 新建一个空数组,包装了原生的 {返回值为Object 的 Array.newInstance()方法},使得支持泛型
     * @param <T> 数组元素类型
     * @param componentType 元素类型
     * @param newSize 大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize){
        return (T[])Array.newInstance(componentType,newSize);
    }


    //------getComponentType---获得数组的元素类型类对象
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
     * 根据数组元素类型，获取数组的类型<br>
     *  param: String.class   ->  [Ljava.lang.String.
     *
     * 方法是通过创建一个空数组从而获取其类型
     * @param componentType
     * @return
     */
    public static Class<?> getArrayType(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }



    /**
     * 强转数组类型<br>
     * 转换的前提是数组元素类型可被强制转换<br>
     * 强制转换后会生成一个新数组
     * @param type   新数组的数组类型或数组元素类型,null的话元素就是Object
     * @param arrayObj
     * @return
     * @throws NullPointerException 待转换数组为空
     * @throws IllegalArgumentException arrayObj不是数组
     */
    public static Object[] cast(Class<?> type, Object arrayObj)throws NullPointerException, IllegalArgumentException{
            if(null==arrayObj){
                throw  new NullPointerException("提供的arrayObj为空");
            }
            if(!arrayObj.getClass().isArray()){
                throw  new IllegalArgumentException("提供的arrayObj不是数组");
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



    /**
     * 判断对象是不是数组类型  obj.getClass().isArray()
     * @param obj
     * @return
     */
    public static boolean isArray(Object obj){

        if(null==obj){
            return false;
        }
        return obj.getClass().isArray();
    }



    /**
     * 数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return 是否包含
     */
    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) > INDEX_NOT_FOUND;
    }




    /**
     * 返回数组中指定元素所在位置，未找到返回{@link #INDEX_NOT_FOUND} =-1
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return 数组中指定元素所在位置
     */
    public static int indexOf(char[] array, char value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    //insert


    /**
     * 将新元素添加到已有数组中<br>
     * 添加新元素会生成一个新的数组，不影响原数组
     * @param <T> 数组元素类型
     * @param buffer 已有数组
     * @param newElements 新元素
     * @return 新数组
     */
    @SafeVarargs
    public static <T> T[] append(T[] buffer, T... newElements) {
        if(isEmpty(buffer)) {
            return newElements;
        }
        return insert(buffer, buffer.length, newElements);
    }


    /**
     * 将新元素插入到到已有数组中的某个位置<br>
     * 添加新元素会生成一个新的数组，不影响原数组<br>
     * index可以为负数，这时从原数组从后向前计数，若大于原数组长度，则空白处用null填充
     *
     * @param <T> 数组元素类型
     * @param buffer 已有数组
     * @param index 插入位置，此位置为对应此位置元素之前的空档
     * @param newElements 新元素
     * @return 新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] insert(T[] buffer, int index, T... newElements) {
        return (T[]) insert((Object)buffer, index, newElements);
    }


    /**
     * 将新元素插入到到已有数组中的某个位置<br>
     * 添加新元素会生成一个新的数组，不影响原数组<br>
     * 如果插入位置为为负数，从原数组从后向前计数，若大于原数组长度，则空白处用null填充
     *
     * @param <T> 数组元素类型
     * @param array 已有数组
     * @param index 插入位置，此位置为对应此位置元素之前的空档
     * @param newElements 新元素
     * @return 新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> Object insert(Object array, int index, T... newElements) {
        if (isEmpty(newElements)) {
            return array;
        }
        if(isEmpty(array)) {
            return newElements;
        }

        final int len = length(array);
        if (index < 0) {
            index = (index % len) + len;
        }

        final T[] result = newArray(array.getClass().getComponentType(), Math.max(len, index) + newElements.length);
        System.arraycopy(array, 0, result, 0, Math.min(len, index));
        System.arraycopy(newElements, 0, result, index, newElements.length);
        if (index < len) {
            System.arraycopy(array, index, result, index + newElements.length, len - index);
        }
        return result;
    }


    //------size--------长度相关---

    /**
     * 获取数组长度<br>
     * array为{@code null}，返回0
     *
     * @param array 对象,不是数组对象会抛异常
     * @return 数组长度
     * @throws IllegalArgumentException 如果参数不为数组，抛出此异常
     * @see  Array#getLength(Object)
     */
    public static int length(Object array) throws IllegalArgumentException {
        if (null == array) {
            return 0;
        }
        return Array.getLength(array);
    }

    //resize---调整数组大小
    public static <T> T[] resize(T[] buffer, int newSize) {
        return resize(buffer,newSize,buffer.getClass().getComponentType());
    }


    /**
     * 生成一个大小调整过的 新的重的数组<br>
     * 经历一次数组拷贝。扩大则占位前N个位置，缩小则截断
     *
     * @param <T> 数组元素类型
     * @param buffer 原数组
     * @param newSize 新的数组大小
     * @param componentType 数组元素类型
     * @return 调整后的新数组
     */
    public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {

        T[] newArray = newArray(componentType, newSize);
        if (isNotEmpty(buffer)) {
            System.arraycopy(buffer, 0, newArray, 0, Math.min(buffer.length, newSize));
        }
        return newArray;
    }


    //combineAll---合并数组

    /**
     * 将多个数组合并在一起<br>
     * 忽略为null的数组,null元素还在</br>
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> T[] combineAll(T[]... arrays){
        if (arrays.length == 1) {
            return arrays[0];
        }
        //合并后数组长度,null数组除外
        int length = 0;
        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
            length += array.length;
        }
        T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);

        length = 0;
        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
            System.arraycopy(array, 0, result, length, array.length);
            length += array.length;
        }
        return result;
    }

    //-----------clone----------克隆--------Equals相同-----------

    /**
     * 针对泛型数组，原生数组clone()方法
     *
     * @param <T> 数组元素类型
     * @param array 被克隆的数组
     * @return 新数组
     */
    public static <T> T[] clone(T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * 针对非泛型数组（原始数据类型元素），原生数组clone()方法
     *
     * @param <T> 数组元素类型
     * @param arrayObj 被克隆的数组对象
     * @return 新数组对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(final T arrayObj) {
        if (arrayObj == null) {
            return null;
        }
        if (isArray(arrayObj)) {
            final Object result;
            final Class<?> componentType = arrayObj.getClass().getComponentType();
            if (componentType.isPrimitive()) {// 原始类型
                int length = Array.getLength(arrayObj);
                result = Array.newInstance(componentType, length);
                while (length-- > 0) {
                    Array.set(result, length, Array.get(arrayObj, length));
                }
            } else {
                result = ((Object[]) arrayObj).clone();
            }
            return (T) result;
        }
        //不是数组,return null
        return null;
    }


    //split 拆分
    /**
     * 把一个byte数组拆分为几个等份（最后一份可能小于len）
     *
     * @param array 数组
     * @param len 每个小节的长度
     * @return 拆分后的数组
     */
    public static byte[][] split(byte[] array, int len) {
        int x = array.length / len; //长度相等的数组 的份数
        int y = array.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy(array, i * len, arr, 0, y);
            } else {
                System.arraycopy(array, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }


    //filter
//    /**
//     * 过滤<br>
//     * 过滤过程通过传入的Editor实现来返回需要的元素内容，这个Editor实现可以实现以下功能：
//     *
//     * <pre>
//     * 1、过滤出需要的对象，如果返回null表示这个元素对象抛弃
//     * 2、修改元素对象，返回集合中为修改后的对象
//     * </pre>
//     *
//     * @param <T> 数组元素类型
//     * @param array 数组
//     * @param editor 编辑器接口
//     * @return 过滤后的数组
//     */
//    public static <T> T[] filter(T[] array, Editor<T> editor) {
//        ArrayList<T> list = new ArrayList<>(array.length);
//        T modified;
//        for (T t : array) {
//            modified = editor.edit(t);
//            if (null != modified) {
//                list.add(modified);
//            }
//        }
//        return list.toArray(Arrays.copyOf(array, list.size()));
//    }
//
//    /**
//     * 过滤<br>
//     * 过滤过程通过传入的Filter实现来过滤返回需要的元素内容，这个Filter实现可以实现以下功能：
//     *
//     * <pre>
//     * 1、过滤出需要的对象，{@link Filter#accept(Object)}方法返回true的对象将被加入结果集合中
//     * </pre>
//     *
//     * @param <T> 数组元素类型
//     * @param array 数组
//     * @param filter 过滤器接口，用于定义过滤规则，null表示不过滤，返回原数组
//     * @return 过滤后的数组
//     * @since 3.2.1
//     */
//    public static <T> T[] filter(T[] array, Filter<T> filter) {
//        if(null == filter) {
//            return array;
//        }
//
//        final ArrayList<T> list = new ArrayList<>(array.length);
//        for (T t : array) {
//            if (filter.accept(t)) {
//                list.add(t);
//            }
//        }
//        final T[] result = newArray(array.getClass().getComponentType(), list.size());
//        return list.toArray(result);
//    }

    //-----zip---对标Python的zip方法
    /**
     *打包 映射键值（参考Python的zip()函数）<br>
     * 例如：<br>
     * keys = [a,b,c,d]<br>
     * values = [1,2,3,4]<br>
     * 则得到的Map是 {a=1, b=2, c=3, d=4}<br>
     * 如果两个数组长度不同，则只对应最短部分
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @param keys 键列表
     * @param values 值列表
     * @param isOrder 是否有序
     * @return Map
     */
    public static <K, V> Map<K, V> zip(K[] keys, V[] values, boolean isOrder) {
        if (isEmpty(keys) || isEmpty(values)) {
            return null;
        }

        final int size = Math.min(keys.length, values.length);
        int initialCapacity = (int) (size / 0.75f) + 1;

        final Map<K, V> map = isOrder ? new LinkedHashMap<>(initialCapacity) : new HashMap<>(initialCapacity);
        for (int i = 0; i < size; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public static <K, V> Map<K, V> zip(K[] keys, V[] values) {
          return zip(keys,values,false);
    }


    // ------------------------------------------------------------------- Wrap and unwrap
    /**
     * 将原始类型数组包装为包装类型
     *
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Integer[] wrap(int... values) {
        if (null == values) {
            return null;
        }
        final int length = values.length;
        if (0 == length) {
            return new Integer[0];
        }

        final Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * 包装类数组转为原始类型数组
     *
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static int[] unWrap(Integer... values) {
        if (null == values) {
            return null;
        }
        final int length = values.length;
        if (0 == length) {
            return new int[0];
        }

        final int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }


    //----字符串-----toString()和join()----------
    /**
     * 数组或集合转String
     *
     * @param obj 集合或数组对象
     * @return 数组字符串，与集合转字符串格式相同
     */
    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        }

        if(obj instanceof long[]){
            return Arrays.toString((long[]) obj);
        } else if(obj instanceof int[]){
            return Arrays.toString((int[]) obj);
        } else if(obj instanceof short[]){
            return Arrays.toString((short[]) obj);
        } else if(obj instanceof char[]){
            return Arrays.toString((char[]) obj);
        } else if(obj instanceof byte[]){
            return Arrays.toString((byte[]) obj);
        } else if(obj instanceof boolean[]){
            return Arrays.toString((boolean[]) obj);
        } else if(obj instanceof float[]){
            return Arrays.toString((float[]) obj);
        } else if(obj instanceof double[]){
            return Arrays.toString((double[]) obj);
        } else if (ArrayUtil.isArray(obj)) {
            // 对象数组
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception ignore) {
                //ignore
            }
        }

        return obj.toString();
    }


    //todo join()


    /**
     * {@link ByteBuffer} 转byte数组
     *
     * @param bytebuffer {@link ByteBuffer}
     * @return byte数组
     */
    public static byte[] toArray(ByteBuffer bytebuffer) {
        if (!bytebuffer.hasArray()) {
            int oldPosition = bytebuffer.position();
            bytebuffer.position(0);
            int size = bytebuffer.limit();
            byte[] buffers = new byte[size];
            bytebuffer.get(buffers);
            bytebuffer.position(oldPosition);
            return buffers;
        } else {
            return Arrays.copyOfRange(bytebuffer.array(), bytebuffer.position(), bytebuffer.limit());
        }
    }




    public static void main(String args[]){
        Integer[] arr = new Integer[]{1,2};

        int[] arr2 = new int[]{1,2};

        System.out.println(clone(arr).equals(arr));
        System.out.println(clone(arr2).equals(arr2));
    }




}
