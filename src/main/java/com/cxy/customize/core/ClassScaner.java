package com.cxy.customize.core;

import com.cxy.customize.core.collection.CollUtil;
import com.cxy.customize.core.collection.EnumerationIter;
import com.cxy.customize.core.io.IOCustomException;
import com.cxy.customize.core.lang.Filter;
import com.cxy.customize.core.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类扫描器
 *
 * 期望:完成类似Spring扫描所有@Component、@Service、@Controller、@Repository注解的类
 * 根据自定义过滤规则得到所有类
 */
public class ClassScaner {
    /** 包名 */
    private String packageName;
    /** 包名，最后跟一个点，表示包名，避免在检查前缀时的歧义 */
    private String packageNameAndDot;
    /** 包路径，用于文件中对路径操作  "com\\cxy\\customize"    */
    private String packageDirName;
    /** 包路径，用于jar中对路径操作，在Linux下与packageDirName一致  "com/cxy/customize"  */
    private String packagePath;
    /** 过滤器 */
    private Filter<Class<?>> classFilter;
    /** 编码 */
    private Charset charset;

    /**
     * 是否初始化类
     * 匹配 加载Class对象时使用{@link Class#forName(String, boolean, ClassLoader)}方法的参数
     */
    private boolean initialize;


    /**
     * 所有Class对象
     */
    private Set<Class<?>> classes = new HashSet<>();

    /**
     * 构造，默认UTF-8编码
     */
    public ClassScaner() {
        this(null);
    }

    /**
     * 构造，默认UTF-8编码
     *
     * @param packageName 包名，所有包传入""或者null
     */
    public ClassScaner(String packageName) {
        this(packageName, null);
    }

    /**
     * 构造
     *
     * @param packageName 包名，所有包传入""或者null
     * @param classFilter 过滤器，无需传入null
     * @param charset 编码
     */
    public ClassScaner(String packageName, Filter<Class<?>> classFilter, Charset charset) {
        packageName = StrUtil.nullToEmpty(packageName);
        this.packageName = packageName;
        this.packageNameAndDot = StrUtil.contactIfNotEndWithSuffix(packageName, StrUtil.DOT,false);
        this.packageDirName = packageName.replace(CharUtil.DOT, File.separatorChar);
        this.packagePath = packageName.replace(CharUtil.DOT, CharUtil.SLASH);
        this.classFilter = classFilter;
        this.charset = charset;
    }


    /**
     * 构造，默认UTF-8编码
     *
     * @param packageName 包名，扫描所有包传入""或者null
     * @param classFilter 过滤器，无需传入null
     */
    public ClassScaner(String packageName, Filter<Class<?>> classFilter) {
        this(packageName, classFilter, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 设置 initialize 属性
     * 是否扫描时初始化
     * @param initialize
     */
    public void setInitialize(boolean initialize) {
        this.initialize = initialize;
    }

    /**
     * 扫描指定包下含有指定注解的类
     */
    public static Set<Class<?>> scanPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return scanPackage(packageName, new Filter<Class<?>>() {
            @Override
            public boolean accept(Class<?> aClass) {
                //目标aClass 有 annotationClass 注解
                return aClass.isAnnotationPresent(annotationClass);
            }
        });
    }


    /**
     * 扫描指定包路径下所有指定类或接口的子类或实现类
     *
     * @param packageName 包路径
     * @param superClass 父类或接口
     * @return 类集合
     */
    public static Set<Class<?>> scanPackageBySuper(String packageName, final Class<?> superClass) {
        return scanPackage(packageName, new Filter<Class<?>>() {
            @Override
            public boolean accept(Class<?> clazz) {
                return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz);
            }
        });
    }


    /**
     * 扫描该包路径下所有class文件
     *
     * @return 类集合
     */
    public static Set<Class<?>> scanPackage() {
        return scanPackage(StrUtil.EMPTY, null);
    }

    /**
     * 扫描该包路径下所有class文件
     *
     * @param packageName 包路径 com | com. | com.abs | com.abs.
     * @return 类集合
     */
    public static Set<Class<?>> scanPackage(String packageName) {
        return scanPackage(packageName, null);
    }

    /**
     * 扫描包路径下满足class过滤器条件的所有class文件，<br>
     * 如果包路径为 com.abs + A.class 但是输入 abs会产生classNotFoundException<br>
     * @param packageName 包路径 com | com. | com.abs | com.abs. 都尽量匹配
     * @param classFilter class过滤器，过滤掉不需要的class
     * @return 类集合
     */
    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
        return new ClassScaner(packageName, classFilter).scan();
    }


    /**
     * 扫描包路径下满足class过滤器条件的所有class文件
     *
     * @return 类集合
     */
    public Set<Class<?>> scan() {
        for (URL url : ResourceUtil.getResourceIter(this.packagePath)) {
            switch (url.getProtocol()) {
                case "file":
                    //根据指定charset解码
                    scanFile(new File(URLUtil.decodeByCharset(url.getFile(), this.charset.name())), null);
                    break;
                case "jar":
                    scanJar(URLUtil.getJarFile(url));
                    break;
            }
        }

        if(CollUtil.isEmpty(this.classes)) {
            scanJavaClassPaths();
        }

        return Collections.unmodifiableSet(this.classes);
    }



    // --------------------------------------------------------------------------------------------------- Private method start
    private static String CLASS_EXT = ".class";
    private static String JAR_FILE_EXT = ".jar";

    /**
     * 扫描Java指定的ClassPath路径
     *
     * @return 扫描到的类
     */
    private void scanJavaClassPaths() {
        final String[] javaClassPaths = ClassUtil.getJavaClassPaths();
        for (String classPath : javaClassPaths) {
            // bug修复，由于路径中空格和中文导致的Jar找不到
            classPath = URLUtil.decodeByCharset(classPath, CharsetUtil.systemCharsetName());

            scanFile(new File(classPath), null);
        }
    }

    /**
     * 扫描文件或目录中的类//todo
     *
     * @param file 文件或目录
     * @param rootDir 包名对应classpath绝对路径
     */
    private void scanFile(File file, String rootDir) {
        if (file.isFile()) {
            final String fileName = file.getAbsolutePath();
            if (fileName.endsWith(CLASS_EXT)) {
                final String className = fileName//
                        // fileName.length() - 6  目的为去掉".class"
                        .substring(rootDir.length(), fileName.length() - 6)//
                        .replace(File.separatorChar, CharUtil.DOT);//
                //加入满足条件的类
                addIfAccept(className);
            } else if (fileName.endsWith(JAR_FILE_EXT)) {
                try {
                    scanJar(new JarFile(file));
                } catch (IOException e) {
                    throw new IOCustomException(e);
                }
            }
        } else if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                scanFile(subFile, (null == rootDir) ? subPathBeforePackage(file) : rootDir);
            }
        }
    }

    /**
     * 扫描jar包
     *
     * @param jar jar包
     */
    private void scanJar(JarFile jar) {
        String name;
        for (JarEntry entry : new EnumerationIter<>(jar.entries())) {
            //去掉 /
            name = StrUtil.removePrefix(entry.getName(), StrUtil.SLASH);
            if (name.startsWith(this.packagePath)) {
                if (name.endsWith(CLASS_EXT) && !entry.isDirectory()) {
                    final String className = name//
                            .substring(0, name.length() - 6)//
                            .replace(CharUtil.SLASH, CharUtil.DOT);//
                    addIfAccept(loadClass(className));
                }
            }
        }
    }

    /**
     * 通过过滤器，是否满足接受此类的条件
     *
     * @param className 类名
     * @return 是否接受
     */
    private void addIfAccept(String className) {
        if(StrUtil.isNotBlank(className)) {
            int classLen = className.length();
            int packageLen = this.packageName.length();
            if (classLen == packageLen) {
                //类名和包名长度一致，传入的包名是类名
                if (className.equals(this.packageName)) {
                    addIfAccept(loadClass(className));
                }
            } else if (classLen > packageLen) {
                //检查类名是否以指定包名为前缀，包名后加.（避免类似于cn.hutool.A和cn.hutool.ATest这类类名引起的歧义）
                if (className.startsWith(this.packageNameAndDot)) {
                    addIfAccept(loadClass(className));
                }
            }
        }
    }

    /**
     * 根据类名获得Class对象
     * @param className
     * @return
     */
    public  Class<?> loadClass(String className){
        Class<?> clazz = null;
        if(StrUtil.isNotBlank(className)){
            try {
                 clazz = Class.forName(className,this.initialize, ClassUtil.getClassLoader());
            }catch (NoClassDefFoundError e) {
                // 由于依赖库导致的类无法加载，直接跳过此类
            } catch (UnsupportedClassVersionError e) {
                // 版本导致的不兼容的类，跳过
        } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return clazz;
    }


    /**
     * 根据类过滤器对类进行判断，通过后加入{@link ClassScaner#classes}
     * @param clazz
     */
    private void addIfAccept(Class<?> clazz){
        if(null != clazz){
            Filter<Class<?>> classFilter = this.classFilter;
            if(classFilter!=null && classFilter.accept(clazz)){
                this.classes.add(clazz);
            }
        }
    }
    /**
     * 截取文件绝对路径中包名之前的部分
     *
     * @param file 文件
     * @return 包名之前的部分
     */
    private String subPathBeforePackage(File file) {
        String filePath = file.getAbsolutePath();
        if (StrUtil.isNotEmpty(this.packageDirName)) {
            filePath = StrUtil.subBefore(filePath, this.packageDirName, true);
        }
        return StrUtil.contactIfNotEndWithSuffix(filePath, File.separator,false);
    }

    // --------------------------------------------------------------------------------------------------- Private method end


}
