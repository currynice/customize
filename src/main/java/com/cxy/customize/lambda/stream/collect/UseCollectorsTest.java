package com.cxy.customize.lambda.stream.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 对list 进行分组操作
 */
public class UseCollectorsTest {
    public static void main(String[] args){
        //3 apple, 2 banana, others 1
        List<String> items = new ArrayList<>(Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya"));

        Map<String,Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        result.forEach((k,v)->System.out.println(k+":"+v));


        List<String> l2 = Arrays.asList("1", "2", "3",
                "4", "5", "6", "7");
        List subList = l2.stream().skip(1).limit(3).collect(Collectors.toList());
        subList.forEach(System.out::println);


        List<String> subList2 = new ArrayList<>(l2.subList(1,4));
        subList2.forEach(System.out::println);
    }
}
