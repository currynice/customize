package com.cxy.customize.core.lang;


import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * 查看声明的修饰符，泛型参数，实现接口，继承关系，以及读取运行时的注释
 */
public class DeclaredSpy {
    public static void main(String[] args) {
        try{
        Class<?> c = Class.forName(args[0]);
        out.format("Class:%n %s%n%n", c.getCanonicalName());
        out.format("Modifier for Class: %n %s%n%n", Modifier.toString(c.getModifiers()));

        out.format("Type Parameters:%n");
        //泛型参数
//        TypeVariable[] tv = c.getTypeParameters();
//        if (tv.length != 0){
//            out.format("  ");
//            for (TypeVariable t : tv)
//                out.format("%s ", t.getName());
//            out.format("%n%n");
//        }else{
//            out.format("-- No TypeParameter --%n%n");
//        }
//        out.format("Inheritance Path:%n");
//        List<Class> l = new ArrayList<>();
//        printAncestor(c, l);
//        if (l.size() != 0) {
//            for (Class<?> cl : l)
//                out.format("  %s%n", cl.getCanonicalName());
//            out.format("%n");
//        } else {
//            out.format("  -- No Super Classes --%n%n");
//        }
//        out.format("Annotations:%n");
//        Annotation[] ann = c.getAnnotations();
//        if (ann.length != 0) {
//            for (Annotation a : ann)
//                out.format("  %s%n", a.toString());
//            out.format("%n");
//        } else {
//            out.format("  -- No Annotations --%n%n");
//        }

    }catch (Exception e){
        e.printStackTrace();
    }
}
    private static void printAncestor(Class<?> c, List<Class> l){
        Class<?> ancestor =c.getSuperclass();
        if (ancestor != null){
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}
