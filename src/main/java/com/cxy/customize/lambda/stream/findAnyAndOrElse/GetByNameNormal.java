package com.cxy.customize.lambda.stream.findAnyAndOrElse;



import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 按名字找到
 */
public class GetByNameNormal {
    public static void main(String[] args){
        List<Developer> list = Arrays.asList(
                new Developer("cxy",new BigDecimal("10000"),21),
                new Developer("da",new BigDecimal("10000"),21),
                new Developer("fad",new BigDecimal("10000"),21),
                new Developer("ef",new BigDecimal("10000"),21),
                new Developer("ij",new BigDecimal("10000"),21),
                new Developer("ui",new BigDecimal("10000"),21)

        );
        Developer result =getByName(list,"cxy");

        System.out.println(result);

    }

    public static Developer getByName(List<Developer> list,String name){
        Developer result =null;
        for (Developer temp : list) {
            if (name.equals(temp.getName())) {
                result = temp;
            }
        }
        return result;
    }
}
