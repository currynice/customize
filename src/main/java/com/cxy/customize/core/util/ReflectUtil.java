package com.cxy.customize.core.util;

import cn.hutool.core.lang.SimpleCache;
import com.cxy.customize.test.A;
import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.lang.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


/**
 * 反射Util
 */
public class ReflectUtil {

    /** 字段缓存 todo 实现自己的缓存池*/
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();

    private ReflectUtil(){

    }

    /**
     * 获得对象的指定field值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj,String fieldName)throws UtilException{
        if(null==obj||StrUtil.isBlank(fieldName)){
            return null;
        }
        Field field = getField(obj.getClass(),fieldName);
        return  getFieldValue(obj,field);
    }

    /**
     * 获得对象的字段值
     * @param obj
     * @param field
     * @return
     */
    public static Object getFieldValue(Object obj,Field field)throws UtilException{
       if(null==obj || field==null){
           return null;
       }
       //针对private static
       field.setAccessible(true);
       Object fieldValue = null;
       try{
           fieldValue = field.get(obj);
       }catch (IllegalAccessException e){
           throw new UtilException(StrUtil.format("IllegalAccess for {}.{}", obj.getClass(), field.getName()),e);
       }
       return fieldValue;
    }

    /**
     *
     *   获得某个类的 <strong>所有字段</strong>：是下面两个jdk方法的并集
     *  获得某个类的所有的公共（public）的字段，包括父类中的字段 {@link Class#getFields()}
     *  获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段{@link Class#getDeclaredFields()}
     * @param typeClass  注意非null
     * @param fieldName
     * @return
     * @throws UtilException
     */
    public static Field getField(Class typeClass,String fieldName){
           final Field[] allfields = getAllField(typeClass);
        if (ArrayUtil.isNotEmpty(allfields)) {
            for (Field field : allfields) {
                if ((fieldName.equals(field.getName()))) {
                    return field;
                }
            }
        }
        return null;
    }

    /**
     * 执行方法
     *
     * @param <T> 返回对象类型
     * @param target 对象，如果执行静态方法，此值为<code>null</code>
     * @param method 方法（对象方法或static方法都可）
     * @param args 参数对象
     * @return 结果
     * @throws UtilException 一些列异常的包装
     */
    @SuppressWarnings("unchecked")
    public static <T> T invoke(Object target, Method method, Object... args) throws UtilException {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        try {
            return (T) method.invoke(ClassUtil.isStatic(method) ? null : target, args);
        } catch (Exception e) {
            throw new UtilException(e);
        }
    }

    //获得typeClass的所有Field
    public static Field[]  getAllField(Class typeClass)throws SecurityException {
        Field[] allFields = FIELDS_CACHE.get(typeClass);
        if (null != allFields) {
            return allFields;
        }
        allFields = getFieldsDirectly(typeClass, true);
        return FIELDS_CACHE.put(typeClass, allFields);
    }

    /**
     * 获得一个type 中所有fields 反射获取，不存入FIELDS_CACHE缓存
     *
     * @param typeClass 类
     * @param withSuperClassFieds 是否继续取父类的字段列表
     * @return Field[]
     * @throws SecurityException 安全检查异常
     */
    public static Field[] getFieldsDirectly(Class<?> typeClass, boolean withSuperClassFieds) throws SecurityException {
        Assert.notNull(typeClass);

        Field[] allFields = null;
        Class<?> searchType = typeClass;
        Field[] declaredFields;
        while (searchType != null) {
            declaredFields = searchType.getDeclaredFields();
            if (null == allFields) {
                allFields = declaredFields;
            } else {
                allFields = ArrayUtil.append(allFields, declaredFields);
            }
            searchType = withSuperClassFieds ? searchType.getSuperclass() : null;
        }

        return allFields;
    }



    enum E{
        A,B
    }

    public static void main(String args[]) throws NoSuchFieldException {
        //testGetClass();
        //testPClass();
        //testForName();
        //testVoid();
       // testSuperclass();
//        testgetClasses();
//        System.out.println("-------");
//        testgetDeclaredClasses();
    }

    private static void testGetClass(){
                Class aClass = "cxy".getClass();
        System.out.println(aClass);
        if( System.console()!=null) {
            Class bClass = System.console().getClass();
            System.out.println(bClass);
        }
        //A是枚举类E的实例，所以getClass()相当于返回E
        Class cClass = E.A.getClass();
        System.out.println(cClass.equals(E.class));//true

        byte[] bytes = new byte[1024];
        byte[][] bytes2 = new byte[10][10];

        Class dClass = bytes.getClass();
        System.out.println(dClass); //class [B
        Class dClass2 = bytes2.getClass();
        System.out.println(dClass2); //class [[B

        Set<String> s = new HashSet<String>();
        Class eClass = s.getClass();
        System.out.println(eClass);//class java.util.HashSet
    }

    private static  void testPClass(){
        System.out.println(byte.class);//byte
        System.out.println(byte[].class);//class [B
        System.out.println(byte[][].class);//class [[B
    }


    private static void  testForName(){
        try {
            Class a = Class.forName("com.cxy.customize.core.date.DateField");
            System.out.println(a.getName());

            Class b = Class.forName("[[B");
            System.out.println(b.getName());

            //String[][].class;
            Class c = Class.forName("[[Ljava.lang.String;");
            System.out.println(c.getName());



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void testVoid(){
       Class a =  Double.TYPE;
       System.out.println(a);
        Class b =  Void.TYPE;
        System.out.println(b);
    }


    private static void testSuperclass(){
        Assert.notNull(javax.swing.JButton.class.getSuperclass());
        Assert.isNull(Object.class.getSuperclass());
        Assert.isNull(Integer.TYPE.getSuperclass());
        Assert.isNull(Void.TYPE.getSuperclass());
        Class c = byte[][].class;
        System.out.println(c.getSuperclass());//class java.lang.Object
    }


    private static void testgetClasses(){
       Class<?>[] classArray =Character.class.getClasses();
       for(Class c :classArray){
           System.out.println(c);
       }
    }

    private static void testgetDeclaredClasses(){
        Class<?>[] classArray =Character.class.getDeclaredClasses();
        for(Class c :classArray){
            System.out.println(c);
        }
    }

    /**
     *获取外围类
     * 针对匿名内部类，有getEnclosingClass方法
     * @throws NoSuchFieldException
     */
    private static void testgetDeclaringClasses() throws NoSuchFieldException {
        Class t =Character.UnicodeBlock.class.getDeclaringClass();

        System.out.println(t);
        System.out.println(Character.UnicodeBlock.class.getEnclosingClass());

        Class t2 =A.t1.class.getDeclaringClass();
        System.out.println(t2);
        System.out.println(A.t1.class.getEnclosingClass());

        Class t3 =System.class.getField("out").getDeclaringClass();
        System.out.println(t3);//class java.lang.System


        System.out.println(InnnerClass.o.getClass().getDeclaringClass());
        System.out.println(InnnerClass.o.getClass().getEnclosingClass());

    }



}
