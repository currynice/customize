package com.cxy.customize.core.util;


import com.cxy.customize.core.io.FileUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 * @description 字符集工具类
 * @author cxy
 * @version 1.1.0
 * @date 2019年6月19日下午6:43:39
 */
public class CharsetUtil {
    private CharsetUtil() {
    }

    /** ISO-8859-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** UTF-8 */
    public static final String UTF_8 = "UTF-8";
    /** GBK */
    public static final String GBK = "GBK";

    /** ISO-8859-1 */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /** UTF-8 */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;

    /** UTF-8 */
    public static final Charset CHARSET_ASCII = StandardCharsets.US_ASCII;

    /** GBK */
    public static final Charset CHARSET_GBK = Charset.forName(GBK);

    //返回jdk支持的字符集名称
    public static List<String> getAllSupportedCharsets(){
        Set<String> names =  Charset.availableCharsets().keySet();
        return new ArrayList<>(names);
    }
    /**
     * 生成字符集
     * @param charsetName  字符集名字，blank返回默认字符集
     * @return
     * @throws UnsupportedEncodingException 编码不支持
     */
    public static  Charset charset(String charsetName) throws UnsupportedEncodingException {
       return StrUtil.isBlank(charsetName)?Charset.defaultCharset():Charset.forName(charsetName);
    }

    /**
     * 转换字符串编码方式
     *   客户端 -》 错误使用GBK编码 -》 Servlet容器 -》 UTF-8解码 -》 乱码
     *   使用方法
     * 	 乱码 -》 UTF-8编码 -》 GBK解码 -》 正确内容
     */
    public static String convert(String source,String sourceCharsetName,String destCharsetName) {
        return convert(source,Charset.forName(sourceCharsetName),Charset.forName(destCharsetName));
    }

    /**
     * 为了让中文字符适应某些特殊要求(如httpheader要求其内容必须为iso8859-1编码),
     * 可能会通过将中文字符按照字节方式来编码的情况,如:
     * String s_iso88591 = newString("中".getBytes("UTF-8"),"ISO8859-1"),
     * 这样得到的s_iso8859-1字符串实际是三个在ISO8859-1中的字符,在将这些字符传递到目的地后,目的地程序再通过相反的方式
     * Strings_utf8 = newString(s_iso88591.getBytes("ISO8859-1"),"UTF-8")来得到正确的中文汉字"中"，
     * 该方法对上述过程做了封装,这样就既保证了遵守协议规定、校验了特殊值，也支持中文。
     * @param source 字符串
     * @param sourceCharset 源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return
     */
    public static String convert(String source,Charset sourceCharset,Charset destCharset){
        if(null==sourceCharset){
            sourceCharset = StandardCharsets.ISO_8859_1;
        }
        if(null==destCharset){
            destCharset = StandardCharsets.UTF_8;
        }
        if(StrUtil.isBlank(source)||null==destCharset){
            return source;
        }
        return new String(StrUtil.bytes(source,sourceCharset),destCharset);
    }



    /**
     * 转换文件编码<br> todo
     * 此方法用于转换文件编码，读取的文件实际编码必须与指定的srcCharset编码一致，否则导致乱码
     *
     * @param file 文件
     * @param srcCharset 原文件的编码，必须与文件内容的编码保持一致
     * @param destCharset 转码后的编码
     * @return 被转换编码的文件
     * @since 3.1.0
     */
//    public static File convert(File file, Charset srcCharset, Charset destCharset) {
//        final String str = FileUtil.readString(file, srcCharset);
//        return FileUtil.writeString(str, file, destCharset);
//    }

    /**
     * 系统字符集编码名称，如果是Windows，则默认为GBK编码，否则取 {@link CharsetUtil#defaultCharsetName()}
     *
     * @return
     * @since 3.1.2
     */
    public static String systemCharsetName() {
        return systemCharset().name();
    }

    /**
     * 系统字符集编码，如果是Windows，则默认为GBK编码，否则取 {@link CharsetUtil#defaultCharset()}
     *
     * @return 系统字符集编码
     */
    public static Charset systemCharset() {
        return FileUtil.isWindows() ? CHARSET_GBK : defaultCharset();
    }

    /**
     * 系统默认字符集名称
     * @return
     */
    public static String defaultCharsetName() {
        return defaultCharset().name();
    }

    /**
     * 系统默认字符集
     * @return
     */
    public static Charset defaultCharset() {
        return Charset.defaultCharset();
    }


    public static void main(String args[]){
        //%E4%B8%AD%E6%96%87
        System.out.println(Arrays.toString(HexUtil.encodeHex(StrUtil.bytes("中文",CHARSET_UTF_8),false)));
    }

}
