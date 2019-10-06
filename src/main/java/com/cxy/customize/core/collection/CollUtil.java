package com.cxy.customize.core.collection;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;


/**
 * 集合相关工具类
 * <p>
 * 此工具方法针对{@link Collection}及其实现类封装的工具。
 * <p>
 */
public class CollUtil {

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }



    public static <T>HashSet<T> newHashSet(T...ts){
        return newHashSet(false,ts);
    }


    @SafeVarargs
    public static <T>HashSet<T> newHashSet(boolean isSorted,T...ts){
        if(ts==null){
            return isSorted?new HashSet<>():new LinkedHashSet<>();
        }
        int initialCapacity = Math.max((int) (ts.length / .75f) + 1, 16);
        HashSet<T> set = isSorted ? new LinkedHashSet<>(initialCapacity) : new HashSet<>(initialCapacity);
        for (T t : ts) {
            set.add(t);
        }
        return set;

    }


}
