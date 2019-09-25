package com.cxy.customize.lambda.stream.fliter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lambda 之前过滤一个list
 */
public class FilterListNormal {
    public static void main(String[] args){
        List<String>  list = Arrays.asList("A","B","C","D");
        List<String>  result =getResultList(list,"B");
        System.out.println("过滤 B 后");
        result.forEach(System.out::println);

    }

    public static List<String> getResultList(List<String> list,String filter){
        List<String>  result = new ArrayList<>();
            for(String str:list){
                if(filter.equals(str)){
                    continue;//跳过
                }
                result.add(str);
        }
        return result;
    }
}
