package com.cxy.customize.lambda.stream.map;

        import java.math.BigDecimal;
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/4/10/010.
 */
public class UseMapToConvertToStringList {
    public static void main(String[] args){
        List<Developer> developerList = Arrays.asList(new Developer("cxy",new BigDecimal("10000"),21),
                new Developer("da",new BigDecimal("10000"),31),
                new Developer("fad",new BigDecimal("10000"),41),
                new Developer("ef",new BigDecimal("10000"),56),
                new Developer("ij",new BigDecimal("10000"),74),
                new Developer("ui",new BigDecimal("10000"),13));

        List<String> nameList = developerList.stream().map(o->o.getName()).collect(Collectors.toList());
        System.out.println(nameList);
    }
}
