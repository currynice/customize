package com.cxy.customize.lambda.sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 不使用Lambda进行排序
 */
public class SortWithoutLambda {
    public static void main(String[] args) {
        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        //重写Comparator规则
//        Collections.sort(listDevs, new Comparator<Developer>() {
//            @Override
//            public int compare(Developer o1, Developer o2) {
//                return o1.getAge()-o2.getAge();
//               // return o1.getAge().compareTo(o2.getAge()); 都行
//            }
//        });
//
//        //sort by name
//        Collections.sort(listDevs, new Comparator<Developer>() {
//            @Override
//            public int compare(Developer o1, Developer o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//
//        //sort by salary
//        Collections.sort(listDevs, new Comparator<Developer>() {
//            @Override
//            public int compare(Developer o1, Developer o2) {
//                return o1.getSalary().compareTo(o2.getSalary());
//            }
//        });
        //List.sort() since Java 8
        listDevs.sort(new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge()-o2.getAge();
            }
        });

        System.out.println("After Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }


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
