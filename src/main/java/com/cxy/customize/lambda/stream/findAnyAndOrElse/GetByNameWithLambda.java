package com.cxy.customize.lambda.stream.findAnyAndOrElse;



import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 *  使用 lambda.filter() to 过滤 a List,
 *  然后 .findAny().orElse (null) 返回一个限定条件的对象.
 */
public class GetByNameWithLambda {
    public static void main(String[] args){
        List<Developer> list = Arrays.asList(
                new Developer("cxy",new BigDecimal("10000"),21),
                new Developer("da",new BigDecimal("10000"),31),
                new Developer("fad",new BigDecimal("10000"),41),
                new Developer("ef",new BigDecimal("10000"),56),
                new Developer("ij",new BigDecimal("10000"),74),
                new Developer("ui",new BigDecimal("10000"),13)
        );
        /**
         * 单条件
         */
      Developer result = list.stream()    //转为管道
                .filter(o->"cxy".equals(o.getName()))   //想要cxy
                .findAny()
                .orElse(null);    //没找到，返回null
        System.out.println(result);

        Developer result2 = list.stream()    //转为管道
                .filter(o->"ooo".equals(o.getName()))
                .findAny()
                .orElse(null);
        System.out.println(result2);


        /**
         * 多条件
         */
        Developer developer = list.stream().filter(o->"cxy".equals(o.getName())&&25==o.getAge())
                                           .findAny().orElse(null);
        System.out.println(developer);
    }





}
