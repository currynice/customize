package com.cxy.customize.lambda.sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 使用Lambda进行排序
 */
public class SortWithLambda {
    public static void main(String[] args) {
        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }


        // List.sort() since Java 8
        // way1   listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());
        // way2   listDevs.sort((o1,o2)->o1.getAge()-o2.getAge());


//        Comparator<Developer> ageComparator = (o1,o2)->o1.getAge()-o2.getAge();
//        listDevs.sort(ageComparator.reversed());
        /**上面两个reversed的话要自己反着写比较规则
         * 或者   把Compator 拿出来再进行反转，这样失去了 lambda 的意义
         * 使用新方式
         */

        listDevs.sort(Comparator.comparing(Developer::getAge).reversed());//反转
        System.out.println("After Sort");

        // listDevs.forEach((developer -> System.out.println(developer))); // old way
       listDevs.forEach(System.out::println);



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
