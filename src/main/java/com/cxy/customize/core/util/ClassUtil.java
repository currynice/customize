package com.cxy.customize.core.util;

import com.cxy.customize.core.ClassScaner;
import com.cxy.customize.core.URLUtil;
import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.lang.Assert;
import com.cxy.customize.core.lang.Filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * Class类方法工具类
 */
public class ClassUtil {
    private ClassUtil() {
    }

    /**
     * 获得obj的Class对象
     * @param obj  为null将返回null
     * @param <T> obj类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T obj){
        return (null==obj)?null: (Class<T>)obj.getClass();
    }


    /**
     * 获得类对象的外围类，即定义此类或匿名类所在的类
     * @return 如果clasz 为基本数据类型,直接定义于包下的类return null
     */
    public static Class<?> getEnclosingClass(Class<?> clasz){
        return (null==clasz)?null:clasz.getEnclosingClass();
    }

    /**
     * clasz是否是顶层类，
     * 即直接定义于包,根据是否有外围类判断
     * @return
     */
    public static boolean isTopLevelClass(Class<?> clasz){
      if(null==clasz){
          return false;
      }
      return null==getEnclosingClass(clasz);
    }


    /**
     * 获取类名
     * @param obj 获取类名对象
     * @param isSimple 是否简单类名，如果为true，返回不带包名的类名
     * @return 类名
     */
    public static String getClassName(Object obj, boolean isSimple) {
        if (null == obj) {
            return null;
        }
        final Class<?> clazz = obj.getClass();
        return getClassName(clazz, isSimple);
    }

    /**
     * 获取类对象对应类类名
     * @param clazz
     * @param isSimple
     * true:XXUtil
     * false:com.cxy.util.XXUtil
     * @return
     */
    public static String getClassName(Class<?> clazz , boolean isSimple) {
        if (null == clazz) {
            return null;
        }
        return isSimple?clazz.getSimpleName():clazz.getName();
    }


    /**
     * 指定类是否与给定的类名相同
     * @param clazz 类
     * @param className 类名，全类名
     * @return 指定类是否与给定的类名相同
     */
    public static boolean equals(Class<?> clazz, String className) {
        if(null==clazz||StrUtil.isBlank(className)){
            return false;
        }
        return className.equals(clazz.getName());
    }
    //// ----------------------------------------------------------------------------------------- Scan classes


    /**
     * 获取{@link ClassLoader}<br>
     * 获取顺序如下：<br>
     *
     * <pre>
     * 1、获取当前线程的ContextClassLoader
     * 2、获取{@link ClassUtil}类对应的ClassLoader
     * 3、获取系统ClassLoader（{@link ClassLoader#getSystemClassLoader()}）
     * </pre>
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        return ClassLoaderUtil.getClassLoader();
    }



    /**
     * 扫面该包路径下所有class文件
     *
     * @return 类集合
     * @see ClassScaner#scanPackage(String, Filter) ()
     */
    public static Set<Class<?>> scanPackage() {
        return ClassScaner.scanPackage();
    }

    /**
     * 扫描指定包路径下所有包含指定注解的类
     *
     * @param packageName 包路径
     * @param annotationClass 注解类
     * @return 类集合
     * @see ClassScaner#scanPackageByAnnotation(String, Class)
     */
    public static Set<Class<?>> scanPackageByAnnotation(String packageName, final Class<? extends Annotation> annotationClass) {
        return ClassScaner.scanPackageByAnnotation(packageName, annotationClass);
    }

    /**
     * 扫描指定包路径下所有指定类或接口的子类或实现类
     *
     * @param packageName 包路径
     * @param superClass 父类或接口
     * @return 类集合
     * @see ClassScaner#scanPackageBySuper(String, Class)
     */
    public static Set<Class<?>> scanPackageBySuper(String packageName, final Class<?> superClass) {
        return ClassScaner.scanPackageBySuper(packageName, superClass);
    }


    /**
     * 扫面该包路径下所有class文件
     *
     * @param packageName 包路径 com | com. | com.abs | com.abs.
     * @return 类集合
     * @see ClassScaner#scanPackage(String)
     */
    public static Set<Class<?>> scanPackage(String packageName) {
        return ClassScaner.scanPackage(packageName);
    }

    /**
     * 扫面包路径下满足class过滤器条件的所有class文件，<br>
     * 如果包路径为 com.abs + A.class 但是输入 abs会产生classNotFoundException<br>
     * 因为className 应该为 com.abs.A 现在却成为abs.A,此工具类对该异常进行忽略处理,有可能是一个不完善的地方，以后需要进行修改<br>
     *
     * @param packageName 包路径 com | com. | com.abs | com.abs.
     * @param classFilter class过滤器，过滤掉不需要的class
     * @return 类集合
     */
    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
        return ClassScaner.scanPackage(packageName, classFilter);
    }
//——————————classpath
    /**
     * @return 获得Java ClassPath路径，不包括 jre
     */
    public static String[] getJavaClassPaths() {
        return System.getProperty("java.class.path").split(System.getProperty("path.separator"));
    }

    /**
     * @return 获得当前线程ClassPath路径
     */
    /**
     * 获得ClassPath
     *
     * @param packageName 包名称
     * @param isDecode 是否解码路径中的特殊字符（例如空格和中文）
     * @return ClassPath路径字符串集合
     * @since 4.0.11
     */
    public static Set<String> getClassPaths(String packageName, boolean isDecode) {
        String packagePath = packageName.replace(StrUtil.DOT, StrUtil.SLASH);
        Enumeration<URL> resources;
        try {
            resources = getClassLoader().getResources(packagePath);
        } catch (IOException e) {
            throw new UtilException( e,"Loading classPath [{}] error!", packagePath);
        }
        final Set<String> paths = new HashSet<String>();
        String path;
        while (resources.hasMoreElements()) {
            path = resources.nextElement().getPath();
            paths.add(isDecode ? URLUtil.decodeByCharset(path, CharsetUtil.systemCharsetName()) : path);
        }
        return paths;
    }

    /**
     * 获取指定类型分的默认值<br>
     * 默认值规则为：
     *
     * <pre>
     * 1、如果为原始类型，返回0
     * 2、非原始类型返回{@code null}
     * </pre>
     *
     * @param clazz 类
     * @return 默认值
     */
    public static Object getDefaultValue(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (long.class == clazz) {
                return 0L;
            } else if (int.class == clazz) {
                return 0;
            } else if (short.class == clazz) {
                return (short) 0;
            } else if (char.class == clazz) {
                return (char) 0;
            } else if (byte.class == clazz) {
                return (byte) 0;
            } else if (double.class == clazz) {
                return 0D;
            } else if (float.class == clazz) {
                return 0f;
            } else if (boolean.class == clazz) {
                return false;
            }
        }

        return null;
    }
//Modifier $ Type

    /**
     * 获得一个类的修饰符描述(接口默认加abstract)
     * @param clazz
     * @return
     */
    public String getModifier(Class<?> clazz){
        if(null!=clazz){
            return Modifier.toString(clazz.getModifiers());
        }
        return null;
    }

    /**
     * 获得泛型参数
     * @param clazz
     * @return
     */
    public static List<String> getTypeVariables(Class<?> clazz){
        List<String> result = new ArrayList<>();
        if(null!=clazz){
            TypeVariable[] tv = clazz.getTypeParameters();
            if (tv.length != 0){
                result =   Arrays.asList(tv).stream().map(o->o.getName()).collect(Collectors.toList());
            }
        }
        return result;
    }

    public static List<Type> getInterfaces(Class<?> clazz){
        List<Type> result = new ArrayList<>();
        if(null!=clazz){
            Type[] intfs = clazz.getGenericInterfaces();
            if (intfs.length != 0){
                result =  Arrays.asList(intfs);
            }
        }
        return result;
    }

    /**
     *判断异常是否是检查型异常(没有RunTimeException)
     * @param eClass
     * @return
     */
    private static boolean isCheckedException(Class<? extends Throwable> eClass){
        return inheritancePath(eClass).contains(RuntimeException.class);
    }

    /**
     * 获得继承关系,数组是Object
     *
     * @param clazz
     * @return
     */
    public static List<Class> inheritancePath(Class<?> clazz){
        List<Class> result = new ArrayList<>();
        if(null!=clazz){
            getAncestor(clazz,result);
        }
        return result;
    }

    private static void getAncestor(Class<?> c,List<Class> result){
        Class<?> ancestor =c.getSuperclass();
        if (ancestor != null){
            result.add(ancestor);
            getAncestor(ancestor, result);
        }
    }

    /**
     * 根据modifier修饰符判断是否为静态方法
     *
     * @param method 方法
     * @return 是否为静态方法
     */
    public static boolean isStatic(Method method) {
        Assert.notNull(method, "Method to provided is null.");
        return Modifier.isStatic(method.getModifiers());
    }



    public static void main(String args[]){

        inheritancePath(TimeoutException.class).forEach(out::println);
        System.out.println(isCheckedException(TimeoutException.class));

        inheritancePath(UtilException.class).forEach(out::println);
        System.out.println(isCheckedException(UtilException.class));
    }
}
