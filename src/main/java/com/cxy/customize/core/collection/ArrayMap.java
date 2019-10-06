package com.cxy.customize.core.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cxy
 * @Date: 2019/10/6 10:15
 * @Description: HashMap 本来是二维结构，但是如果内部元素比较少，使用二维结构反而浪
 * 费空间，还不如使用一维数组进行存储，需要查找时，因为元素少进行遍历也很快，甚至可
 * 以比 HashMap 本身的查找还要快。比如下面我们可以使用数组来模拟 HashMap 的增删改
 * 操作
 */
public class ArrayMap<K,V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    /**
     *  增
     * @param k
     * @param v
     * @return  原来key对应的value
     */
    public V put(K k,V v){
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(k)){
                V oldv = values.get(i);
                values.set(i,v);
                return oldv;
            }
        }
        keys.add(k);
        values.add(v);
        return null;
    }


    public V get(K k){
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(k)){
                return values.get(i);
            }
        }
        return null;
    }


    public V delete(K k){
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(k)){
                keys.remove(i);
                return values.remove(i);
            }
        }
        return null;
    }



    public static void main(String args[]){
        ArrayMap<String,String> arrayMap= new ArrayMap<>();
        System.out.println(arrayMap.put("name","cxy"));
        System.out.println(arrayMap.put("age","13"));
        System.out.println(arrayMap.delete("age"));
        System.out.println(arrayMap.get("name"));
        System.out.println(arrayMap.get("age"));
    }

}
