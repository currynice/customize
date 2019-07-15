package com.cxy.customize.core.annotation.sample.combination;

import com.cxy.customize.core.annotation.AnnotationUtil;

import java.lang.annotation.Annotation;

/**
 * 组合注解Test,遍历的原理
 */
public class CombinationTest {


    public static void main(String[] args){
//        Test test = new Test();
//
//        System.out.println("jdk不支持组合注解");
//        Annotation[]  annotations=  test.getClass().getAnnotations();
//        for(Annotation annotation:annotations){
//            System.out.println(annotation.annotationType());//interface com.cxy.customize.core.annotation.sample.combination.B，jdk 不支持组合注解
//        }
//        System.out.println("递归实现组合注解");
//        Annotation[]  annotations2 =    AnnotationUtil.toCombination(test.getClass()).getAnnotations();
//
//        for(Annotation annotation2:annotations2){
//            System.out.println(annotation2.annotationType());//interface com.cxy.customize.core.annotation.sample.combination.A && interface com.cxy.customize.core.annotation.sample.combination.B
//        }

        System.out.println( AnnotationUtil.isInherited(A.class));


    }


}
