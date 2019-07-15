package com.cxy.customize.core.annotation.sample.dynamically_edit;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CXY {
     String value() default "";
    String name() default "";
}
