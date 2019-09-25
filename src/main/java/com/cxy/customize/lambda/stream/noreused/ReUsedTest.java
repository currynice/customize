package com.cxy.customize.lambda.stream.noreused;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Exception:java.lang.IllegalStateException:
 * lambda has already been operated upon or closed
 */
public class ReUsedTest {
    public static void main(String[] args){
        String[] arrays = {"a","b","c","d"};
//       Stream<String> lambda =  Arrays.lambda(arrays);
//       lambda.forEach(System.out::println);//不能重新使用
     /*确实需要重新使用*/
        Supplier<Stream<String>> supplier = ()->Arrays.stream(arrays);
        supplier.get().forEach(System.out::println);
        supplier.get().forEach(o->System.out.println("reUsed"+o));
    }

}
