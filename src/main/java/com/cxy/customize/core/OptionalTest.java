package com.cxy.customize.core;

import java.util.Arrays;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] rags){

        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("Dolores");

        Optional empty = Optional.ofNullable(null);

        System.out.println(name.orElse("1"));
        System.out.println(empty.orElse("2"));

        System.out.println(name.orElseGet(()->"xxx"));
        System.out.println(empty.orElseGet(()-> Arrays.asList("1","2","3")));
    }
}
