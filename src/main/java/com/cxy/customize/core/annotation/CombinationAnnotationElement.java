package com.cxy.customize.core.annotation;


import com.cxy.customize.core.collection.CollUtil;

import javax.annotation.*;
import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;
import java.util.*;


/**
 * 组合注解元素
 * cxy
 */
public class CombinationAnnotationElement implements AnnotatedElement {


    /**
     * 元注解
     */
    private static final Set<Class<?extends Annotation>> META_ANNOTATIONS = CollUtil.newHashSet(
            Target.class,
            Repeatable.class,
            Retention.class, //
            Inherited.class, //
            Documented.class, //
            SuppressWarnings.class, //
            Override.class,
            Deprecated.class,
            Generated.class,
            PostConstruct.class,
            PreDestroy.class,
            Resource.class,
            Resources.class
            );

    /** 注解类型与注解对象对应表 */
    private Map<Class<? extends Annotation>, Annotation> annotationMap;
    /** 直接注解类型与注解对象对应表 */
    private Map<Class<? extends Annotation>, Annotation> declaredAnnotationMap;

    /**
     * 构造
     *
     * @param element 需要解析注解的元素：可以是Class、Method、Field、Constructor、ReflectPermission
     */
    public CombinationAnnotationElement(AnnotatedElement element) {
        init(element);
    }

    /**
     *  是否存在注解xxx
     * @param annotationClass
     * @return
     */
    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
/*       return getAnnotation(annotationClass) != null; //都行*/
        return this.annotationMap.containsKey(annotationClass);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
       Annotation annotation =  this.annotationMap.get(annotationClass);
       return annotation==null?null:(T)annotation;
    }

    /**
     * 不知道注解类型的情况下获取在类、方法、变量上面的全部注解
     */
    @Override
    public Annotation[] getAnnotations() {
        final Collection<Annotation> collection = this.annotationMap.values();
        return collection.toArray(new Annotation[collection.size()]);
    }

    //注解是可以继承的，子类会继承父类的一些注解（该注解在定义时一定要声明为@Inherited，而且方法和变量上的注解不一定能继承
    //不会返回继承的注解

    /**
     * 注解是可以继承的，子类会继承父类的一些注解（该注解在定义时一定要声明为@Inherited，而且方法和变量上的注解不一定能继承
     * 不会返回继承的注解
     * @return
     */
    @Override
    public Annotation[] getDeclaredAnnotations() {
       final Collection<Annotation> collection = this.declaredAnnotationMap.values();
       return collection.toArray(new Annotation[collection.size()]);
    }

    /**
     * 初始化
     * @param element
     */
    private void init(AnnotatedElement element){
        Annotation[] declaredAnnotations = element.getDeclaredAnnotations();
        this.declaredAnnotationMap = new HashMap<>();
        parseDeclared(declaredAnnotations);
        Annotation[] annotations = element.getAnnotations();
        if(declaredAnnotations==annotations){
            this.annotationMap = this.declaredAnnotationMap;
        }else{
            this.annotationMap = new HashMap<>();
            parse(annotations);
        }
    }

    /**
     * 进行递归解析注解，直到全部都是元注解为止
     *
     * @param annotations Class, Method, Field等
     */
    private void parseDeclared(Annotation[] annotations) {
        Class<? extends Annotation> annotationType;
        // 直接注解
        for (Annotation annotation : annotations) {
            annotationType = annotation.annotationType();
            if (!META_ANNOTATIONS.contains(annotationType)) {
                declaredAnnotationMap.put(annotationType, annotation);
                parseDeclared(annotationType.getDeclaredAnnotations());
            }
        }
    }

    /**
     * 进行递归解析注解，直到全部都是元注解为止
     */
    private void parse(Annotation[] annotations) {
        Class<? extends Annotation> annotationType;
        for (Annotation annotation : annotations) {
            annotationType = annotation.annotationType();
            if (!META_ANNOTATIONS.contains(annotationType)) {
                annotationMap.put(annotationType, annotation);
                parse(annotationType.getAnnotations());
            }
        }
    }

}
