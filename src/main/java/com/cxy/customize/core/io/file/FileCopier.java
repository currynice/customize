package com.cxy.customize.core.io.file;

import cn.hutool.core.util.StrUtil;
import com.cxy.customize.core.io.FileUtil;
import com.cxy.customize.core.io.IOCustomException;
import com.cxy.customize.core.lang.Assert;
import com.cxy.customize.core.lang.copier.CopierTool;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件拷贝器
 * 1.文件文件之间的
 */
public class FileCopier extends CopierTool<File,FileCopier> {
    /** 是否覆盖目标文件 */
    private boolean isOverride;
    /** 是否拷贝所有属性 */
    private boolean isCopyAttributes;
    /** 当拷贝来源是目录时是否只拷贝目录下的内容 */
    private boolean isCopyContentIfDir;
    /** 当拷贝来源是目录时是否只拷贝文件而忽略子目录 */
    private boolean isOnlyCopyFile;

    //构造模式
    public static FileCopier create(File src,File dest){
        return new FileCopier(src,dest);
    }

    public static FileCopier create(String srcPath,String destPath){
        return new FileCopier(new File(srcPath),new File(destPath));
    }



    public FileCopier(File src,File dest) {
             this.src = src;
             this.dest = dest;
    }

    public boolean isOverride() {
        return isOverride;
    }

    public FileCopier setOverride(boolean override) {
        isOverride = override;
        return this;
    }

    public boolean isCopyAttributes() {
        return isCopyAttributes;
    }

    public FileCopier setCopyAttributes(boolean copyAttributes) {
        isCopyAttributes = copyAttributes;
        return this;
    }

    public boolean isCopyContentIfDir() {
        return isCopyContentIfDir;
    }

    public FileCopier setCopyContentIfDir(boolean copyContentIfDir) {
        isCopyContentIfDir = copyContentIfDir;
        return this;
    }

    public boolean isOnlyCopyFile() {
        return isOnlyCopyFile;
    }

    public FileCopier setOnlyCopyFile(boolean onlyCopyFile) {
        isOnlyCopyFile = onlyCopyFile;
        return this;
    }

    /**
     * 执行拷贝<br>
     * 拷贝规则为：
     * <pre>
     * 1、源为文件，目标为已存在目录，则拷贝到目录下，文件名不变
     * 2、源为文件，目标为不存在路径，则目标以文件对待（自动创建父级目录）比如：/dest/aaa，如果aaa不存在，则aaa被当作文件名
     * 3、源为文件，目标是一个已存在的文件，则当{@link #setOverride(boolean)}设为true时会被覆盖，默认不覆盖
     * 4、源为目录，目标为已存在目录，当{@link #setCopyContentIfDir(boolean)}为true时，只拷贝目录中的内容到目标目录中，否则整个源目录连同其目录拷贝到目标目录中
     * 5、源为目录，目标为不存在路径，则自动创建目标为新目录，然后按照规则4复制
     * 6、源为目录，目标为文件，抛出IO异常
     * 7、源路径和目标路径相同时，抛出IO异常
     * </pre>
     *
     * @return 拷贝后目标的文件或目录
     * @throws IOCustomException IO异常
     */
    @Override
    public File copy() {
        final File src =this.src;
        final File dest =this.dest;
        //源为null，不存在，抛异常
        Assert.notNull(src,"源src不能为空");
        if(!src.exists()){
            throw new  IOCustomException("源src不存在");
        }

        Assert.notNull(dest,"目标dest不能为空");
        //两个file相同,复制失败抛异常
        if(FileUtil.isSameFile(src,dest)){
            throw new  IOCustomException("src can not be allowing  equals to dest ");
        }
        if (src.isDirectory()) {// 复制目录
            if(dest.exists() && !dest.isDirectory()) {
                //源为目录，目标为文件，抛出IO异常
                throw new IOCustomException("Src is a directory but dest is a file!");
            }
            final File subDest = isCopyContentIfDir ? dest : FileUtil.mkdir(FileUtil.file(dest, src.getName()));
            copyDirContent(src, subDest);
        } else {// 复制文件
            copyFile(src, dest);
        }
        return dest;

    }

    /**
     * 拷贝目录内容，只用于内部，不做任何安全检查<br>
     *  拷贝目录下文件夹(包含里面的东西)以及文件
     * 不拷贝源目录本身
     * 4、源为目录，目标为已存在目录，当{@link #setCopyContentIfDir(boolean)}为true时，只拷贝目录中的内容到目标目录中，否则整个源目录连同其目录拷贝到目标目录中
     * 5、源为目录，目标为不存在路径，则自动创建目标为新目录，然后按照规则4复制
     * 6、源为目录，目标为文件，抛出IO异常
     *
     * @param src 源目录
     * @param dest 目标目录
     * @throws IOCustomException IO异常
     */
    private void copyDirContent(File src, File dest) throws IOCustomException {
        if (null != copyFilter && !copyFilter.accept(src)) {
            //被过滤的目录跳过
            return;
        }

        if (!dest.exists()) {
            //目标为不存在路径，创建为目录
            dest.mkdirs();
        } else if (dest.isFile()) {
            //目标为文件,无法拷贝
            throw new IOCustomException(StrUtil.format("Src [{}] is a directory but dest [{}] is a file!", src.getPath(), dest.getPath()));
        }

        final String files[] = src.list();
        File srcFile;
        File destFile;
        for (String file : files) {
            srcFile = new File(src, file);
            destFile = this.isOnlyCopyFile ? dest : FileUtil.file(dest, file);
            // 递归复制
            if (srcFile.isDirectory()) {
                copyDirContent(srcFile, destFile);
            } else {
                copyFile(srcFile, destFile);
            }
        }
    }

    /**
     * 拷贝文件，src必须确定了是文件
     * 1、源为文件，目标为已存在目录，则拷贝到目录下，文件名不变
     * 2、源为文件，目标为不存在路径，则目标以文件对待（自动创建父级目录）比如：/dest/aaa，如果aaa不存在，则aaa被当作文件名
     * 3、源为文件，目标是一个已存在的文件，则当{@link #setOverride(boolean)}设为true时会被覆盖，默认不覆盖
     *
     * @param src
     * @param dest
     */
    private void copyFile(File src,File dest) throws IOCustomException{
        //考虑src没有通过 过滤器逻辑
        if(copyFilter!=null && !copyFilter.accept(src)){
            return;
        }

        if(dest.exists()){
            if (dest.isDirectory()){
                //dest为已存在目录，则拷贝到目录下，文件名不变
                dest = FileUtil.file(dest,src.getName());
            }
            //目标是一个已存在的文件,考虑override属性
            if (!isOverride){
                return;
            }
        }else{
            //不存在，创建
            dest.getParentFile().mkdirs();
        }
        List<StandardCopyOption>  copyOptionList = new ArrayList<>(2);
        if(isOverride){
            copyOptionList.add(StandardCopyOption.REPLACE_EXISTING);
        }else if(isCopyAttributes){
            copyOptionList.add(StandardCopyOption.COPY_ATTRIBUTES);
        }
        FileUtil.copyFile(src,dest,copyOptionList.toArray(new StandardCopyOption[copyOptionList.size()]));
    }
}
