package com.cxy.customize.lambda.sortMap.HashMap;

import java.util.*;

/**
 *
 */
public class HashMapTest {
    public static void main(String []args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "dads");
        map.put(14, "adad");
        map.put(18, "wq");
        map.put(2, "wq");

        map.forEach((k,v)->System.out.println(k+"="+v));

        List<Map.Entry<Integer, String>> mapList = new ArrayList<>(map.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<Integer, String>>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for(Map.Entry<Integer,String> m:mapList){
            System.out.println("排序后,"+m.getKey()+"="+m.getValue());
        }
    }
}
