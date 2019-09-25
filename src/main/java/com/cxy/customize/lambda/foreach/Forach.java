package com.cxy.customize.lambda.foreach;

import java.util.*;

/**
 * 使用lambda 进行遍历,List的遍历写了无数次了，换成Map 玩玩
 */
public class Forach {
    public static void main(String[] args) {
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);


        /**
         * Iterator 进行Map的遍历
         */
        /*
        Iterator<Map.Entry<String, Integer>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if ("D".equals(entry.getKey())) {
                iterator.remove();
            }else{
                System.out.println("key:" + entry.getKey() + "---value:" + entry.getValue());
            }
        }
         */
        /**
         * lambda forEach Map  remove时会有ConcurrentModificationException
         */
        items.forEach((k,v)->System.out.println("key:" + k + "---value:" + v));

        /**
         * lambda forEach Map  remove时会有ConcurrentModificationException,还是用Iterator进行删除
         */
        /*
        items.forEach((k,v)->{
            if("D".equals(k)){
                items.remove(k);
            }else{
                System.out.println("key:" + k + "---value:" + v);
            }
           });
        */
        System.out.println("LIST LIST LIST LIST LIST LIST");

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        //lambda
        //Output : A,B,C,D,E
//        list.forEach(str->System.out.println(str));
//        System.out.println("--------");
//
//        //Output : C
//        list.forEach(str->{
//            if("C".equals(str)){
//                System.out.println(str);
//            }
//        });
//        System.out.println("--------");
//
//        //method reference
//        //Output : A,B,C,D,E
//        list.forEach(System.out::println);
//        System.out.println("--------");

        //Stream and filter
        //Output : B
        list.stream()
                .filter(s->s.contains("B"))
                .forEach(System.out::println);








    }
}
