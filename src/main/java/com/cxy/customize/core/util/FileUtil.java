package com.cxy.customize.core.util;

import java.io.File;

/**
 *
 * @description 文件工具类
 * @author cxy
 * @version 1.1.0
 * @date 2019年6月19日下午6:43:39
 */
public class FileUtil {

    private FileUtil() {
    }

    // window 的分隔符'\\';
    private static final char WINDOWS_SPERATOR = CharUtil.BACKSLASH;


    /**
     * 判断当前操作系统是否是windows
     * 通过File.separatorChar来判断
     * @return
     */
    public static boolean isWindows(){
        return WINDOWS_SPERATOR== File.separatorChar;
    }

}
