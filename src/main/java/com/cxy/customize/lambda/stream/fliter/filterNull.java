package com.cxy.customize.lambda.stream.fliter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 过滤 Stream 里null 值
 * filter with Objects::nonNull
 */
public class filterNull {
    public static  void main(String[] args){
        Stream<String> langugae = Stream.of("java", "python", "node", null, "ruby", null, "php");
//        List<String> list = langugae.collect(Collectors.toList());
//        List<String> result =langugae.filter(o->o!=null).collect(Collectors.toList());
        List<String> result = langugae.filter(Objects::nonNull).collect(Collectors.toList());
        result.forEach(System.out::println);

    }
}
