//package com.cxy.customize.core.annotation.sample;
//
//
//import com.bms.common.exception.EnumErrorException;
//
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//
//
///**
// * 自定义 枚举注解
// * 用于 excel 导入验证
// */
//@Target({ ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = EnumLimit.Validator.class)
//public @interface EnumLimit {
//
//    String message() default "";
//
//    Class<?>[] groups() default { };
//
//    Class<? extends Payload>[] payload() default { };
//
//    /**
//     * 枚举类
//     */
//    Class<? extends Enum<?>> enumClass();
//
//    /***枚举方法*/
//    String enumMethod();
//
//    class Validator implements ConstraintValidator<EnumLimit, String> {
//        private Class<? extends Enum<?>> enumClass;
//
//        private String enumMethod;
//
//        @Override
//        public void initialize(EnumLimit limit) {
//            enumClass = limit.enumClass();
//            enumMethod = limit.enumMethod();
//        }
//
//
//        //注解下字段是否有效
//        @Override
//        public boolean isValid(String value, ConstraintValidatorContext context){
//
//            if (value == null||"".equals(value)) {
//                return Boolean.TRUE;
//            }
//            if (enumClass == null || enumMethod == null) {
//                return Boolean.TRUE;
//            }
//
//            Class<?> valueClass = value.getClass();
//
//            try {
//                Method method = enumClass.getMethod(enumMethod, valueClass);
//                if (!Integer.TYPE.equals(method.getReturnType()) && !Integer.class.equals(method.getReturnType())) {
//                    throw new EnumErrorException(String.format("%s中%s方法返回类型不是Integer类型", enumMethod, enumClass));
//                }
//
//                if(!Modifier.isStatic(method.getModifiers())) {
//                    throw new EnumErrorException(String.format("%s中%s不是静态方法", enumClass, enumMethod));
//                }
//                return (Integer)method.invoke(null, value)!=-1;
//            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                throw new EnumErrorException(e);
//            } catch (NoSuchMethodException | SecurityException e) {
//                throw new EnumErrorException(String.format("%s(%s) 方法不在%s中", enumMethod, valueClass, enumClass), e);
//            }
//
//        }
//    }
//
//}
