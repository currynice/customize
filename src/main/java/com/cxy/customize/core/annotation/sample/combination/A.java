package com.cxy.customize.core.annotation.sample.combination;

import java.lang.annotation.*;

/**
 * 元注解A
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface A {
}
