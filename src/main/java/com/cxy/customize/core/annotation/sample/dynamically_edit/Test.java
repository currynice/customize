package com.cxy.customize.core.annotation.sample.dynamically_edit;

import com.cxy.customize.core.annotation.AnnotationUtil;

import java.lang.reflect.Field;

public class Test {

    public static void main(String ...args) throws Exception {
//        City city = new City();
//        //获取字段
//        Field field = City.class.getDeclaredField("address");
//        //获取注解实例
//        CXY cxy = field.getAnnotation(CXY.class);
//
//        InvocationHandler annotationInvocationHandler = Proxy.getInvocationHandler(cxy);
//        Field hfield = annotationInvocationHandler.getClass().getDeclaredField("memberValues");
//        // 因为这个字段为 private final 修饰，所以要打开权限
//        hfield.setAccessible(true);
//        Map<String, Object> memberValues =( Map<String, Object>)hfield.get(annotationInvocationHandler);
//        memberValues.put("value","北京");
//        String value = cxy.value();
//        System.out.println(value);

        AnnotationUtil.dynamicallyEditValue(City.class,"address",CXY.class,"value","东京");
        City city = new City();
        //获取字段
        Field field = City.class.getDeclaredField("address");
        //获取注解实例
        CXY cxy = field.getAnnotation(CXY.class);
                String value = cxy.value();
        System.out.println(value);

    }
}
