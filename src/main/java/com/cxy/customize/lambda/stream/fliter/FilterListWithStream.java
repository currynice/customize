package com.cxy.customize.lambda.stream.fliter;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在管道中进行中间操作 之 filter() 过滤一个list
 *                       collect(collect.toList())  stream转换成List
 */
public class FilterListWithStream {
    public static void main(String[] args){
        List<String> list = Arrays.asList("A","B","C","D");
        List<String>  result = list.stream().filter(str->!"B".equals(str)).collect(Collectors.toList());
        System.out.println("过滤 B 后");
        result.forEach(System.out::println);
    }

}
