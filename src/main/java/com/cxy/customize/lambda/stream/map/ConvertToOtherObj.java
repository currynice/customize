package com.cxy.customize.lambda.stream.map;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 转成别的对象stream.不要一个一个的转了
 */
public class ConvertToOtherObj {
    public static void main(String[] args){
        List<Developer> developerList = Arrays.asList(new Developer("cxy",new BigDecimal("10000"),21),
                new Developer("da",new BigDecimal("10000"),31),
                new Developer("fad",new BigDecimal("10000"),41),
                new Developer("ef",new BigDecimal("10000"),56),
                new Developer("ij",new BigDecimal("10000"),74),
                new Developer("ui",new BigDecimal("10000"),13));
            developerList.forEach(System.out::println);
            System.out.println("抓换后");
        List<Award> awardList = developerList.stream().map(o->{
            Award award = new Award();
            award.setName(o.getName());
            /*BigDecimal 做乘法需要 a.multiply(b)方法*/
            BigDecimal year = new BigDecimal(o.getAge());
            BigDecimal savings = o.getSalary().multiply(year);
            award.setSavings(savings);
            return award;
        }).collect(Collectors.toList());

        awardList.forEach(System.out::println);

    }
}
