package com.cxy.customize.lambda.sortMap.TreeMap;

import java.util.*;

/**
 * TreeMap 升序的(key)，使用Comparator 进行排序
 */
public class TreeMapTest {
    public static void main(String[] args){
        /*降序排列的TreeMap*/
        Map<String,String> map = new TreeMap<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        //默认升序，反过来，降序
                        return o2.compareTo(o1);
                    }
                }
            );
        map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + map.get(key));
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("按value排序");
        /**
         * 根据value排序
         */
        Map<String,String> map2 = new TreeMap<>();
        map2.put("c", "ccccc");
        map2.put("a", "aaaaa");
        map2.put("b", "bbbbb");
        map2.put("d", "ddddd");

        List<Map.Entry<String,String>> entryList = new ArrayList<>(map2.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                //升序value排序
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String,String> result = new LinkedHashMap<>();
        for(Map.Entry<String,String> sortMap:entryList){
         result.put(sortMap.getKey(),sortMap.getValue());
        }

        result.forEach((k,v)->System.out.println(k+"="+v));
    }




}
