package com.cxy.customize.core.io;


import com.cxy.customize.core.io.file.FileCopier;
import com.cxy.customize.core.io.file.FileModeEnum;
import com.cxy.customize.core.lang.Assert;
import com.cxy.customize.core.util.ArrayUtil;
import com.cxy.customize.core.util.CharUtil;
import com.cxy.customize.core.util.StrUtil;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @description 文件工具类
 * 参考todo
 * https://www.cnblogs.com/noteless/p/9609837.html
 * @author cxy
 * @version 1.1.0
 * @date 2019年6月19日下午6:43:39
 */
public class FileUtil {

    private FileUtil() {
    }
    // 类似Unix 的分隔符'/';
    private static final char UNIX_SPERATOR = CharUtil.SLASH;

    // window 的分隔符'\\';
    private static final char WINDOWS_SPERATOR = CharUtil.BACKSLASH;

    //windows文件名的无效字符9个  \ / : * ? " < > |
    private static Pattern INVALID_CHARACTER_WIN = Pattern.compile("[\\\\/:*?\"<>|]");




    /**
     * 判断当前操作系统是否是windows
     * 通过File.separatorChar来判断
     * @return
     */
    public static boolean isWindows(){
        return WINDOWS_SPERATOR== File.separatorChar;
    }


    public static boolean isUnix(){
        return UNIX_SPERATOR == File.separatorChar;
    }

    /**
     * 列出目录文件<br>
     * 给定的绝对路径不能是压缩包中的路径
     *
     * @param path 目录绝对路径或者相对路径
     * @return 文件列表（包含目录）
     */
    public static File[] ls(String path) {
        if (path == null) {
            return null;
        }
        File file = new File(path);
        if (file.isDirectory()) {
            return file.listFiles();
        }
        throw new IOCustomException(StrUtil.format("Path [{}] is not directory!", path));
    }

    /**
     * 是否为空
     * 目录下没有文件为空 ，文件length为空
     * @param file
     * @return
     */
    public static boolean isEmpty(File file){
        if(null==file){
            return true;
        }

        if(file.isDirectory()){
            String [] fileDirs  = file.list();
            if(ArrayUtil.isEmpty(fileDirs)){
                return true;
            }
        }else if (file.isFile()){
            return file.length() <= 0;
        }
        return false;
    }

    /**
     * 是否为非空
     * @param file
     * @return
     */
    public static  boolean isNotEmpty(File file){
        return !isEmpty(file);
    }


    /**
     * 目录是否为空
     * @param path
     * @return
     */
    public static boolean isDirEmpty(Path path){
        try {
            DirectoryStream<Path>  dirStream=  Files.newDirectoryStream(path);
            return !dirStream.iterator().hasNext();
        } catch (IOException e) {
            throw new IOCustomException(e);
        }
    }


    /**
     * 目录是否为空
     * @param dir
     * @return
     */
    public static boolean isDirEmpty(File dir){
       return isDirEmpty(dir.toPath());
    }

    /**
     * 遍历文件目录及子目录下所有file
     * @param file     不是目录或为空返回空列表
     * @return
     */
    public static List<File>  loopFiles(File file){
        return loopFiles(file,null);
    }

    /**
     * 遍历文件目录及子目录下所有符合条件的file
     * @param file     不是目录或为空返回空列表
     * @param fileFilter  文件过滤器实现
     * @return
     */
    public static List<File>  loopFiles(File file,FileFilter fileFilter){
        List<File> fileList = new ArrayList<>();
        if(null==file||!file.exists()){
            return fileList;
        }
         if(file.isDirectory()){
             //子文件集(按照规则实现过滤)
             final File[] subFiles = file.listFiles();
             if(ArrayUtil.isNotEmpty(subFiles)){
                 for(File f:subFiles){
                    fileList.addAll(loopFiles(f,fileFilter));
                 }
             }
        }else{
             //符合或没有过滤规则，添加进链表
             if(null==fileFilter||fileFilter.accept(file)){
                 fileList.add(file);
             }
         }
         return fileList;
    }


    //getPath ,get


    /**
     * 检查父完整路径是否为子路径的前半部分，如果不是说明不是子路径，可能存在slip注入。
     * <p>
     * 见http://blog.nsfocus.net/zip-slip-2/
     *
     * @param parentFile 父文件或目录
     * @param file 子文件或目录
     * @return 子文件或目录
     * @throws IllegalArgumentException 检查创建的子文件不在父目录中抛出此异常
     */
    public static File checkSlip(File parentFile, File file) throws IllegalArgumentException {
        if (null != parentFile && null != file) {
            String parentCanonicalPath;
            String canonicalPath;
            try {
                parentCanonicalPath = parentFile.getCanonicalPath();
                canonicalPath = file.getCanonicalPath();
            } catch (IOException e) {
                throw new IOCustomException(e);
            }
            if (!canonicalPath.startsWith(parentCanonicalPath)) {
                throw new IllegalArgumentException("New file is outside of the parent dir: " + file.getName());
            }
        }
        return file;
    }

    /**
     * 获取临时文件路径（绝对路径）
     *
     * @return 临时文件路径
     */
    public static String getTmpDirPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取用户路径（绝对路径）
     *
     * @return 用户路径
     * @since 4.0.6
     */
    public static String getUserHomePath() {
        return System.getProperty("user.home");
    }




    /**
     * 删除路径下所有文件(包括文件本身)
     * @param path
     */
    public static void deleteFilesOfPath(String path){
        File file = new File(path);
        if (!file.exists()){
            throw new IllegalArgumentException(StrUtil.format("目录找不到了"));
        }
        //不是文件夹
        if (!file.isDirectory()){
            file.delete();
        }else {
            File [] files = file.listFiles();
            if (files!=null && files.length>0){
                //有文件
                for(File f:files){
                    if (!f.isDirectory()){
                        f.delete();
                    }else {
                        deleteFilesOfPath(f.getAbsolutePath());
                    }
                }
                file.delete();
            }
        }
    }

    /**
     * 根据父文件路径以及文件路径创建文件对象，检查了是否是同一路径下的，防止slip注入
     * @param parent
     * @param path
     * @return
     */
    public static File file(File parent,String path){
        if(StrUtil.isBlank(path)){
            throw new NullPointerException("path 不能为空");
        }
        return checkSlip(parent, new File(parent, path));
    }


    /**
     * 创建文件夹，会递归自动创建其不存在的父文件夹，如果存在直接返回此文件夹<br>
     * 此方法不对File对象类型做判断，如果File不存在，无法判断其类型
     *
     * @param dir 目录
     * @return 创建的目录
     */
    public static File mkdir(File dir) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }



    /**
     * 是否存在匹配文件
     *
     * @param directory 文件夹路径
     * @param regexp 文件夹中所包含文件名的正则表达式
     * @return 如果存在匹配文件返回true
     */
    public static boolean exist(String directory, String regexp) {
        final File file = new File(directory);
        if (!file.exists()) {
            return false;
        }

        final String[] fileList = file.list();
        if (fileList == null) {
            return false;
        }

        for (String fileName : fileList) {
            if (fileName.matches(regexp)) {
                return true;
            }

        }
        return false;
    }

    /**
     * 计算目录或文件的总大小<br>
     * 当给定对象为文件时，直接调用 {@link File#length()}<br>
     * 当给定对象为目录时，遍历目录下的所有文件和目录，递归计算其大小，求和返回
     *
     * @param file 目录或文件
     * @return 总大小，bytes长度
     */
    public static long size(File file) {
        Assert.notNull(file, "file argument is null !");
        if (!file.exists()) {
            throw new IllegalArgumentException(StrUtil.format("File [{}] not exist !", file.getAbsolutePath()));
        }

        if (file.isDirectory()) {
            long size = 0L;
            File[] subFiles = file.listFiles();
            if (ArrayUtil.isEmpty(subFiles)) {
                return 0L;// empty directory
            }
            for (int i = 0; i < subFiles.length; i++) {
                size += size(subFiles[i]);
            }
            return size;
        } else {
            return file.length();
        }
    }

    public static File mkParentDirs(File file){
        final File parentFile = file.getParentFile();
        if (null != parentFile && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return parentFile;
    }


    //————————copy——————
    /**
     * 通过JDK7+的 {@link Files#copy(Path, Path, CopyOption...)} 方法拷贝文件
     *
     * @param src 源文件路径
     * @param dest 目标文件或目录
     * @param options {@link StandardCopyOption}
     * @return Path
     * @throws IOCustomException IO异常
     */
    public static Path copyFile(Path src,Path dest,StandardCopyOption ...options)throws IOCustomException {

        Assert.notNull(src,"源目录或文件为空");
        Assert.notNull(dest,"目标目录或文件为空");
        //foo/bar ”，则使用路径字符串“ gus ”调用此方法将导致Path “ foo/bar/gus
        Path realDestPath = src.toFile().isDirectory()? dest.resolve(src.getFileName()):dest;
        try {
          return   Files.copy(src,realDestPath,options);
        } catch (IOException e) {
            throw new IOCustomException(e);
        }
    }


    public static File copyFile(File src, File dest, StandardCopyOption... options) throws IOCustomException {
        // check
        Assert.notNull(src, "Source File is null !");
        if (!src.exists()) {
            throw new IOCustomException("File not exist: " + src);
        }
        Assert.notNull(dest, "Destination File or directiory is null !");
        if (isSameFile(src, dest)) {
            throw new IOCustomException("Files '{}' and '{}' are equal", src, dest);
        }
        return copyFile(src.toPath(), dest.toPath(), options).toFile();
    }

    public static File copyFile(String src, String dest, StandardCopyOption... options) throws IOCustomException {
        // check
        Assert.notBlank(src, "Source File is null !");
        Assert.notBlank(dest, "Destination File or directiory is null !");
        return copyFile(Paths.get(src),Paths.get(dest),options).toFile();
    }

    /**
     * 检查两个文件是否是同一个文件<br>
     * 所谓文件相同，是指File对象是否指向同一个文件或文件夹（都不存在，路径相同也是true的）
     * @param file1
     * @param file2
     * @return
     * @throws IOCustomException
     */
    public static boolean isSameFile(File file1, File file2) throws IOCustomException {
        Assert.notNull(file1);
        Assert.notNull(file2);
        //有一个不存在
        if(!file1.exists()||!file2.exists()){
            //两个都不存在(对路径进行比较)
          if(!file1.exists()&&!file2.exists()){
                return isPathEquals(file1,file2);
            }
          //一个存在一个不存在，false
            return false;
        }
        //都存在
        try {
            return Files.isSameFile(file1.toPath(),file2.toPath());
        } catch (IOException e) {
           throw new IOCustomException(e);
        }
    }


    /**
     * 比较file路径
     * File类重写了equals方法，重写String 的compare方法
     * (win下的jdk只有Win的实现类，所以，为了比较也可以比较类Unix下的路径,依照compare的规则，制定可以通用的比较规则 )，win 系统忽略大小写, 类Unix系统区分大小写
     * @return
     */
    public static boolean isPathEquals(File file1,File file2){
        boolean result =false;
        //win
       if(isWindows()){
           try {
               result = StrUtil.equalsIgnoreCase(file1.getCanonicalPath(),file2.getCanonicalPath());
           } catch (IOException e) {
               result = StrUtil.equalsIgnoreCase(file1.getAbsolutePath(),file2.getAbsolutePath());
           }
       }
        if(isUnix()){
            try {
                result = StrUtil.equals(file1.getCanonicalPath(),file2.getCanonicalPath(),false);
            } catch (IOException e) {
                result = StrUtil.equals(file1.getAbsolutePath(),file2.getAbsolutePath(),false);
            }
        }
        return result;
    }

    /**
     * 复制文件或目录<br>
     * 情况如下：
     *
     * <pre>
     * 1、src和dest都为目录，则将src目录及其目录下所有文件目录拷贝到dest下
     * 2、src和dest都为文件，直接复制，名字为dest
     * 3、src为文件，dest为目录，将src拷贝到dest目录下
     * </pre>
     *
     * @param src 源文件
     * @param dest 目标文件或目录，目标不存在会自动创建（目录、文件都创建）
     * @param isOverride 是否覆盖目标文件
     * @return 目标目录或文件
     * @throws IOCustomException IO异常
     */
    public static File copy(File src, File dest, boolean isOverride) throws IOCustomException {
        return FileCopier.create(src, dest).setOverride(isOverride).copy();
    }

    /**
     * 复制文件或目录<br>
     * 情况如下：
     *
     * <pre>
     * 1、src和dest都为目录，则讲src下所有文件目录拷贝到dest下
     * 2、src和dest都为文件，直接复制，名字为dest
     * 3、src为文件，dest为目录，将src拷贝到dest目录下
     * </pre>
     *
     * @param src 源文件
     * @param dest 目标文件或目录，目标不存在会自动创建（目录、文件都创建）
     * @param isOverride 是否覆盖目标文件
     * @return 目标目录或文件
     * @throws IOCustomException IO异常
     */
    public static File copyContent(File src, File dest, boolean isOverride) throws IOCustomException {
        return FileCopier.create(src, dest).setCopyContentIfDir(true).setOverride(isOverride).copy();
    }

    /**
     * 复制文件或目录<br>
     * 情况如下：
     *
     * <pre>
     * 1、src和dest都为目录，则讲src下所有文件（包括子目录）拷贝到dest下
     * 2、src和dest都为文件，直接复制，名字为dest
     * 3、src为文件，dest为目录，将src拷贝到dest目录下
     * </pre>
     *
     * @param src 源文件
     * @param dest 目标文件或目录，目标不存在会自动创建（目录、文件都创建）
     * @param isOverride 是否覆盖目标文件
     * @return 目标目录或文件
     * @throws IOCustomException IO异常
     */
    public static File copyFilesFromDir(File src, File dest, boolean isOverride) throws IOCustomException {
        return FileCopier.create(src, dest).setCopyContentIfDir(true).setOnlyCopyFile(true).setOverride(isOverride).copy();
    }




    public static void main(String [] args) throws IOException {


}

    //——————RandomAccessFile start
    // 1.对文件内容（读操作和写操作）的访问
    // 2.支持随机访问文件，以及访问文件的任意位置
    // 硬盘存储上文件存储的方式为byte数据的集合
    // 打开方式 rw(读写) 和 r(只读)
    /**
     * 创建RandomAccessFile
     * @param path
     * @param mode
     * @return
     */
    public static RandomAccessFile createRandomAccessFile(Path path, FileModeEnum mode){
        try {
            return new RandomAccessFile(path.toFile(),mode.name());
        } catch (FileNotFoundException e) {
            throw new IOCustomException(e);
        }
    }

    /**
     * 创建RandomAccessFile
     * @param file
     * @param mode
     * @return
     */
    public static RandomAccessFile createRandomAccessFile(File file, FileModeEnum mode){
        try {
            return new RandomAccessFile(file,mode.name());
        } catch (FileNotFoundException e) {
           throw new IOCustomException(e);
        }
    }


    /**
     * 创建RandomAccessFile
     * @param name 文件名
     * @param modeName
     * @return
     */
    public static RandomAccessFile createRandomAccessFile(String name,String modeName){
        try {
            return new RandomAccessFile(name,modeName);
        } catch (FileNotFoundException e) {
            throw new IOCustomException(e);
        }
    }

    //——————RandomAccessFile end




}
