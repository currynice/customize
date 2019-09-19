package com.cxy.customize.core.annotation;



import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Map;

/**
 * 注解工具类(那些ajava.lang.annotation.RetentionPolicy 是 RUNTIME 才能访问)
 */
public class AnnotationUtil {

    /** todo
     * 动态修改注解的值
     * @param targetClass 修改的类
     * @param fieldName   修改的字段
     * @param annotationClass 字段上的注解
     * @param annotationKey   修改的key
     * @param value   修改的值
     */
    public static void dynamicallyEditValue(Class<?> targetClass,String fieldName,Class<? extends Annotation> annotationClass,String annotationKey,Object value) throws  NoSuchFieldException,IllegalAccessException {
        try{
            //获取字段
            Field field = targetClass.getDeclaredField(fieldName);
            //获取注解实例
            Object annotation = field.getAnnotation(annotationClass);

            InvocationHandler annotationInvocationHandler = Proxy.getInvocationHandler(annotation);
            Field hfield = annotationInvocationHandler.getClass().getDeclaredField("memberValues");
            // 因为这个字段为 private final 修饰，所以要打开权限
            hfield.setAccessible(true);
            Map<String, Object> memberValues =( Map<String, Object>)hfield.get(annotationInvocationHandler);
            memberValues.put(annotationKey,value);
        }catch (NoSuchFieldException e){
             throw new NoSuchFieldException("没有该字段");
        }catch (IllegalAccessException e){
            throw new IllegalAccessException("字段访问失败");
        }
    }


    /**
     * 将指定的被注解的元素转换为组合注解元素
     * @return 组合注解元素
     */
    public static CombinationAnnotationElement toCombination(AnnotatedElement annotationEle) {
        if(annotationEle instanceof cn.hutool.core.annotation.CombinationAnnotationElement) {
            return (CombinationAnnotationElement)annotationEle;
        }
        return new CombinationAnnotationElement(annotationEle);
    }
                      
    /**
     * 获取指定注解的
     * @param annotationEle 注解元素 可以是Class、Method、Field、Constructor、Package,ReflectPermission
     * @return
     */
    public static Annotation[] getAnnotations(AnnotatedElement annotationEle){
        return annotationEle==null?null:toCombination(annotationEle).getAnnotations();
    }

    /**
     * 获取指定注解
     * @param annotationEle 注解元素 可以是Class、Method、Field、Constructor、Package,ReflectPermission
     * @param annotationType 注解类型
     * @return
     */
    public static  <A extends Annotation >A getAnnotation(AnnotatedElement annotationEle,Class<A> annotationType){
        return annotationEle==null?null:toCombination(annotationEle).getAnnotation(annotationType);
    }

    /**
     * 获取指定注解s
     * @param annotationEle 注解元素 可以是Class、Method、Field、Constructor、Package,ReflectPermission
     * @param isCombination 是否是组合注解
     * @return
     */
    public static Annotation[]   getAnnotations(AnnotatedElement annotationEle,boolean isCombination){
        if(annotationEle!=null){
        return  isCombination?toCombination(annotationEle).getAnnotations():annotationEle.getAnnotations();
        }
        return null;
    }

    /**
     * 获取指定注解指定属性的值
     * @param annotationEle    注解元素 可以是Class、Method、Field、Constructor、Package,ReflectPermission
     * @param annotationType   注解
     * @param propertyName     属性名 name()  即 name
     * @param <T>              注解值类型
     * @return
     */
//    public static <T>T getAnnatationValue(AnnotatedElement annotationEle,Class<? extends Annotation> annotationType,String propertyName) {
//        ////获取注解实例
//        final Annotation annotation = getAnnotation(annotationEle, annotationType);
//        if (null == annotation) {
//            return null;
//        }
//
//        final Method method = ReflectUtil.getMethodOfObj(annotation, propertyName);
//    }

    /**
     * 获取注解类的保留时间： SOURCE（源码时），CLASS（编译时，默认），RUNTIME（运行时）
     * @param annotationType
     * @return
     */
    public static RetentionPolicy getRetentionPolicy(Class<? extends Annotation> annotationType){
          final Retention retention = annotationType.getAnnotation(Retention.class);
          if(null==retention){
           return RetentionPolicy.CLASS;
          }
          return  retention.value();
    }

    /**
     *  取注解类的修饰范围： 默认为所有
     * @param annotationType
     * @return
     */
    public static ElementType[] getTarget(Class<? extends Annotation> annotationType){
         final Target target = annotationType.getAnnotation(Target.class);
          if(null==target){
              return new    ElementType[]{ ElementType.FIELD,
                      ElementType.FIELD, //
                      ElementType.METHOD, //
                      ElementType.PARAMETER, //
                      ElementType.CONSTRUCTOR, //
                      ElementType.LOCAL_VARIABLE, //
                      ElementType.ANNOTATION_TYPE, //
                      ElementType.PACKAGE//
              };
          }
          return target.value();
    }

    /**
     * 是否会保存到 Javadoc 文档中
     * @return annotationType
     */
    public static boolean isDocumented(Class<? extends Annotation> annotationType){
         return  annotationType.isAnnotationPresent(Documented.class);
    }

    /**
     * 注解是否可以被继承 ,默认为false
     * @return annotationType
     */
    public static boolean isInherited(Class<? extends Annotation> annotationType){
        return  annotationType.isAnnotationPresent(Inherited.class);
    }

    /**
     * 某个类是否标记为Deprecated
     * @param clasz
     * @return
     */
    public static boolean isDeprecated(Class clasz){
        return clasz.isAnnotationPresent(Deprecated.class);
    }

    /**
     * 某个类中某方法是否标记为Deprecated
     * @param clasz
     * @param methodName
     * @return
     */
        public static Boolean isDeprecated(Class clasz,String methodName,Class ...para){
            try {
                return clasz.getMethod(methodName,para).isAnnotationPresent(Deprecated.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
           return null;
        }


}
