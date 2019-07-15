package com.cxy.customize.core.annotation.sample.combination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解B,使用了注解A，但是不会生效
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@A
public @interface B {
}
