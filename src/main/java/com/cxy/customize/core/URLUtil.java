package com.cxy.customize.core;


import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.io.IOCustomException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.jar.JarFile;

public class URLUtil {

    /**
     * 根据指定字符集解码,异常抛给上层处理
     * @param url
     * @param charsetNmae
     * @return
     */
    public static String decodeByCharset(String url,String charsetNmae)throws UtilException{
        if(null == url){
            return null;
        }
        try {
        return  URLDecoder.decode(url,charsetNmae);
        } catch (UnsupportedEncodingException e) {
            throw new UtilException("解码失败{"+charsetNmae+"}",e);
        }
    }

    /**
     * 从URL中获取JarFile
     *
     * @param url URL
     * @return JarFile
     * @since 4.1.5
     */
    public static JarFile getJarFile(URL url) {
        try {
            JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
            return urlConnection.getJarFile();
        } catch (IOException e) {
            throw new IOCustomException(e);
        }
    }
}
