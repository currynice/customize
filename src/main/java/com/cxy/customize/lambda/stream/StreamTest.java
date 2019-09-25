package com.cxy.customize.lambda.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 把Collection 转换成 管道源.
 *  Array 转换成 管道源
 */
public class StreamTest {
    public static void main(String[] args){

        List<Developer> developerList =getDevelopers();
        developerList.stream().forEach(System.out::println);
        System.out.println("---------");
        Developer[] developers =developerList.toArray(new Developer[developerList.size()]);
        Arrays.stream(developers).forEach(System.out::println);
        /**
         * 两个一样的
         *  public static<T> Stream<T> of(T... values) {
            return Arrays.lambda(values);
            }
         */
        Stream.of(developers).forEach(System.out::println);
    }

    private static List<Developer> getDevelopers(){
        List<Developer> result = new ArrayList<>();
        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));
        return result;
    }
}
