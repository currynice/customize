package com.cxy.customize.lambda.stream.collect;

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
        List<String> items = Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String,Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        result.forEach((k,v)->System.out.println(k+":"+v));

    }
}
