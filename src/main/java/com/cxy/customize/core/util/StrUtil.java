package com.cxy.customize.core.util;

import com.cxy.customize.core.text.StrFormatter;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


/**
 * 字符串工具类
 */
public class StrUtil {

    public static final int INDEX_NOT_FOUND = -1;

    public static final char C_SPACE = CharUtil.SPACE;
    public static final char C_TAB = CharUtil.TAB;
    public static final char C_DOT = CharUtil.DOT;
    public static final char C_SLASH = CharUtil.SLASH;
    public static final char C_BACKSLASH = CharUtil.BACKSLASH;
    public static final char C_CR = CharUtil.CR;
    public static final char C_LF = CharUtil.LF;
    public static final char C_UNDERLINE = CharUtil.UNDERLINE;
    public static final char C_COMMA = CharUtil.COMMA;
    public static final char C_DELIM_START = CharUtil.DELIM_START;
    public static final char C_DELIM_END = CharUtil.DELIM_END;
    public static final char C_BRACKET_START = CharUtil.BRACKET_START;
    public static final char C_BRACKET_END = CharUtil.BRACKET_END;
    public static final char C_COLON = CharUtil.COLON;

    public static final String SPACE = " ";
    public static final String TAB = "	";
    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String EMPTY = "";
    public static final String NULL = "null";
    //回车
    public static final String CR = "\r";
    //换行
    public static final String LF = "\n";
    //回车换行
    public static final String CRLF = "\r\n";
    public static final String UNDERLINE = "_";
    public static final String DASHED = "-";
    public static final String COMMA = ",";
    public static final String DELIM_START = "{";
    public static final String DELIM_END = "}";
    public static final String BRACKET_START = "[";
    public static final String BRACKET_END = "]";
    public static final String COLON = ":";

    //HTML中空格
    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp;";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_APOS = "&apos;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";

    public static final String EMPTY_JSON = "{}";

    /**
     * 字符串是否为空
     *  空的定义:
     *  null  ""   "   "如空格
     * @param str  String ,StringBuffer,StringBuilder等等
     * @return
     */
    public static boolean isBlank(CharSequence str){
        int length;
        if(str==null||(length=str.length())==0){
            return true;
        }
        for(int i=0;i<length;i++){
            // 只要有一个非空字符即为非空字符串
            if(!CharUtil.isBlankChar(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    /**
     * 字符串是否为非空
     * @param str  String ,StringBuffer,StringBuilder等等
     * @return
     */
    public static boolean isNotBlank(CharSequence str){
        return !isBlank(str);
    }

    /**
     *  是否包含空字符串
     * @param strs  String ,StringBuffer,StringBuilder等等
     * @return
     */
    public static boolean hasBlank(CharSequence... strs){
        if(ArrayUtil.isEmpty(strs)){
            return true;
        }
        for(CharSequence str:strs){
            if(isBlank(str)){
                return true;
            }
        }
        return false;
    }


    /**
     *  给定字符串是否全部为blank
     * @param strs  String ,StringBuffer,StringBuilder等等
     * @return
     */
    public static boolean isAllBlank(CharSequence... strs){
        if(ArrayUtil.isEmpty(strs)){
            return true;
        }
        for(CharSequence str:strs){
            if(isNotBlank(str)){
                return false;
            }
        }
        return false;
    }

    // ------------------------------------------------------------------------ Empty
    /**
     * 字符串是否为 empty，空的定义如下:<br>
     * 1、为null <br>
     * 2、为""<br>
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 字符串是否为 非empty
     * @param str
     * @return
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }


    /**
     * 是否包含empty字符串
     * @param strs 字符串列表
     * @return
     */
    public static boolean hasEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        }
        for (CharSequence str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否全部为空字符串
     * @param strs 字符串列表
     * @return
     */
    public static boolean isAllEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return true;
        }
        for (CharSequence str : strs) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }


    // ------------------------------------------------------------------------ Trim
    /**
     * 除去字符串尾部的空白，如果字符串是 null，或结果为"",则返回null。
     *
     * 注意，和String.trim不同，此方法使用CharUtil.isBlankChar来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     *
     * trimEnd(null)       = null
     * trimEnd(&quot;&quot;)         = &quot;&quot;
     * trimEnd(&quot;abc&quot;)      = &quot;abc&quot;
     * trimEnd(&quot;  abc&quot;)    = &quot;  abc&quot;
     * trimEnd(&quot;abc  &quot;)    = &quot;abc&quot;
     * trimEnd(&quot; abc &quot;)    = &quot; abc&quot;
     *
     *
     * @param str 要处理的字符串
     *
     * @return
     */
    public static String trimEnd(CharSequence str) {
        return trim(str, 1);
    }

    public static String trimStart(CharSequence str) {
        return trim(str, -1);
    }

    public static String trimStartEnd(CharSequence str) {
        return trim(str, 0);
    }
    /**
     * 除去字符串头尾部的空白符，如果字符串是 null，或结果为"",则返回null。
     *
     * @param str 要处理的字符串
     * @param mode trim模式: -1:表示trimStart，0:表示trim全部，1:表示trimEnd
     *
     * @return
     */
    public static String trim(CharSequence str, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            while ((start < end) && (CharUtil.isBlankChar(str.charAt(start)))) {
                start++;
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            while ((start < end) && (CharUtil.isBlankChar(str.charAt(end - 1)))) {
                end--;
            }
        }

        if ((start > 0) || (end < length)) {
            return str.toString().substring(start, end);
        }

        return str.toString();
    }

    /**
     * str是否以给定字符c开始
     * @param str
     * @param c
     * @return
     */
    public static boolean isStartWith(CharSequence str,char c){
        return c==str.charAt(0);
    }


    /**
     * 是否以指定字符串开头<br>
     * 如果给定的字符串和开头字符串都为null则返回true，否则任意一个值为null返回false
     *
     * @param str 被监测字符串
     * @param prefix 开头字符串
     * @param isIgnoreCase 是否忽略大小写
     * @return
     */
    public static boolean startWith(CharSequence str, CharSequence prefix, boolean isIgnoreCase) {
        if (null == str || null == prefix) {
            if (null == str && null == prefix) {
                return true;
            }
            return false;
        }

        if (isIgnoreCase) {
            return str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
        } else {
            return str.toString().startsWith(prefix.toString());
        }
    }

    /**
     * 是否以指定字符串开头
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startWith(CharSequence str, CharSequence prefix) {
        return startWith(str,prefix,true);
    }

    /**
     * 是否以指定字符串开头，忽略大小写
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return startWith(str,prefix,false);
    }

    /**
     * 给定字符串是否以任何一个字符串开始<br>
     * 给定字符串和数组为空都返回false
     *
     * @param str 给定字符串
     * @param prefixes 需要检测的开始字符串
     * @return
     */
    public static boolean startWithAny(CharSequence str, CharSequence... prefixes) {
        if (isEmpty(str) || ArrayUtil.isEmpty(prefixes)) {
            return false;
        }

        for (CharSequence suffix : prefixes) {
            if (startWith(str, suffix, false)) {
                return true;
            }
        }
        return false;
    }

    /**
     * str是否以给定字符c结尾
     * @param str
     * @param c
     * @return
     */
    public static boolean isEndWith(CharSequence str,char c){

        return c==str.charAt(str.length()-1);
    }


    /**
     * 是否以指定字符串结尾<br>
     * 如果给定的字符串和结尾字符串都为null则返回true，否则任意一个值为null返回false
     *
     * @param str 被监测字符串
     * @param suffix 结尾字符串
     * @param isIgnoreCase 是否忽略大小写
     * @return
     */
    public static boolean endWith(CharSequence str, CharSequence suffix, boolean isIgnoreCase) {
        if (null == str || null == suffix) {
            if (null == str && null == suffix) {
                return true;
            }
            return false;
        }

        if (isIgnoreCase) {
            return str.toString().toLowerCase().endsWith(suffix.toString().toLowerCase());
        } else {
            return str.toString().endsWith(suffix.toString());
        }
    }

    /**
     * 是否以指定字符串结尾
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endWith(CharSequence str, CharSequence suffix) {
        return endWith(str,suffix,true);
    }

    /**
     * 是否以指定字符串结尾，忽略大小写
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return endWith(str,suffix,false);
    }

    /**
     * 给定字符串是否以任何一个字符串结尾<br>
     * 给定字符串和数组为空都返回false
     *
     * @param str 给定字符串
     * @param suffixs 需要检测的结尾字符串
     * @return
     */
    public static boolean endWithAny(CharSequence str, CharSequence... suffixs) {
        if (isEmpty(str) || ArrayUtil.isEmpty(suffixs)) {
            return false;
        }

        for (CharSequence suffix : suffixs) {
            if (endWith(str, suffix, false)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否被指定的前缀后缀 字符串 所包围
     * @return   是否包围，空串不包围
     */
    public static boolean isSurround(CharSequence str,CharSequence start,CharSequence end){
        if(isBlank(str)){
            return  false;
        }
        //给定的长度小于前缀后缀之和
        if(str.length()<(start.length()+end.length())){
            return false;
        }
        return startWith(str,start)&&endWith(str,end);
    }


    /**
     * 判断字符串是否被指定的前缀后缀 字符 所包围
     * @return   是否包围，空串不包围
     */
    public static boolean isSurround(CharSequence str,char start,char end){
        if(isBlank(str)){
            return  false;
        }
        //给定的长度小于前缀后缀之和(2)
        if(str.length()<2){
            return false;
        }
        return str.charAt(0)==start&&str.charAt(str.length()-1)==end;
    }

    //...upper And lower

    /**
     * 首字母大写
     * @param str
     * str = "string"
     * @return   "String"
     */
    public static String upperFirst(CharSequence str){
        if(null==str){
            return null;
        }
        if(str.length()>0){
            char firstChar = str.charAt(0);
            //小写字母
            if(Character.isLowerCase(firstChar)){
                return Character.toUpperCase(firstChar)+subSuf(str, 1);
            }
        }
        return str.toString();
    }


    /**
     * 首字母小写
     * @param str
     * str = "String"
     * @return   "string"
     */
    public static String lowerFirst(CharSequence str){
        if(null==str){
            return null;
        }
        if(str.length()>0){
            char firstChar = str.charAt(0);
            //大写字母
            if(Character.isUpperCase(firstChar)){
                return Character.toLowerCase(firstChar)+subSuf(str, 1);
            }
        }
        return str.toString();
    }
    /**
     * 截取分隔字符串之前的字符串，不包括分隔字符串<br>
     * 如果给定的字符串为空串（null或""）或者分隔字符串为null，返回原字符串<br>
     * 如果分隔字符串为空串""，则返回空串，如果分隔字符串未找到，返回原字符串，举例如下：
     *
     * <pre>
     * StrUtil.subBefore(null, *)      = null
     * StrUtil.subBefore("", *)        = ""
     * StrUtil.subBefore("abc", "a")   = ""
     * StrUtil.subBefore("abcba", "b") = "a"
     * StrUtil.subBefore("abc", "c")   = "ab"
     * StrUtil.subBefore("abc", "d")   = "abc"
     * StrUtil.subBefore("abc", "")    = ""
     * StrUtil.subBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param string 被查找的字符串
     * @param separator 分隔字符串（不包括）
     * @param isLastSeparator 是否查找最后一个分隔字符串（多次出现分隔字符串时选取最后一个），true为选取最后一个
     * @return 切割后的字符串
     * @since 3.1.1
     */
    public static String subBefore(CharSequence string, CharSequence separator, boolean isLastSeparator) {
        if (isEmpty(string) || separator == null) {
            return null == string ? null : string.toString();
        }

        final String str = string.toString();
        final String sep = separator.toString();
        if (sep.isEmpty()) {
            return EMPTY;
        }
        final int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
        if (INDEX_NOT_FOUND == pos) {
            return str;
        }
        if (0 == pos) {
            return EMPTY;
        }
        return str.substring(0, pos);
    }



    //..........removeXXX 去除前缀后缀


    /**
     * 去除指定字符串的前缀
     * tips:1.不以指定前缀开头将返回原字符串
     * @return
     */
    public static String removePrefix(CharSequence str,CharSequence prefix){
        if(isEmpty(str)||isEmpty(prefix)){
            return str(str);
        }
        if(startWith(str,prefix)){
            return subSuf(str, prefix.length());// 截取后半段
        }
        return str(str);
    }


    /**
     * 去掉指定后缀(忽略大小写了)
     *
     * @param str 字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (isEmpty(str) || isEmpty(suffix)) {
            return str(str);
        }


        if (endWith(str,suffix)) {
            return subPre(str, str.length() - suffix.length());// 截取前半段
        }
        return str(str);
    }

//--------sub

    /**
     * 针对String.subString()越界问题进行封装<br>
     * index从0开始计算，最后一个字符为-1(模仿python)<br>
     * 如果from和to位置一样，返回 "" <br>
     * 如果from或to为负数，则按照length从后向前数位置，如果绝对值大于字符串长度，则from归到0，to归到length<br>
     * 如果经过修正的index中from大于to，则互换from和to example: <br>
     * abcdefgh 2 3 =》 c <br>
     * abcdefgh 2 -3 =》 cde <br>
     *
     * @see String#substring(int)
     * @param str
     * @param fromIndex 开始的index（包括）
     * @param toIndex 结束的index（不包括）
     * @return
     */
    public static String sub(CharSequence str, int fromIndex, int toIndex){
        if(isEmpty(str)){
            return str(str);
        }
        int len = str.length();

        // 修正开始，如果from 为负数
        if(fromIndex<0){
            //按照length从后向前数位置
            fromIndex+=len;
            //绝对值小于字符串长度
            if(fromIndex<0){
                //from变成0
                fromIndex = 0;
            }
        }//开始位置就大于字符串长度
        else if (fromIndex > len) {
            fromIndex =len;
        }

        //to 为负数
        if(toIndex<0){
            //按照length从后向前数位置
            toIndex+=len;
            //绝对值小于字符串长度
            if(fromIndex<0){
                //to变成len
                toIndex = len;
            }
        }//结束位置就大于字符串长度
        else if (toIndex > len) {
            toIndex =len;
        }

        //修正结束,如果此时from 还是大于 to,二者互换位置
        if(fromIndex>toIndex){
            int i = fromIndex;
            fromIndex = toIndex;
            toIndex = i;
        }
        //二者相等的话,返回""
        if (fromIndex == toIndex) {
            return EMPTY;
        }
        return str(str).substring(fromIndex,toIndex);

    }

    /**
     * 限制字符串长度，超出部分使用...
     * @param string
     * @param limit    限制长度
     * @return
     */
    public static String maxLength(CharSequence string,int limit){
        if(isEmpty(string)){
            return str(string);
        }
        if(string.length()<=limit){
            return string.toString();
        }
        return sub(string,0,limit)+"...";
    }

    /**
     * 切割指定位置之前部分的字符串
     *
     * @param string 字符串
     * @param toIndex 切割到的位置（不包括）
     * @return 切割后的剩余的前半部分字符串
     */
    public static String subPre(CharSequence string, int toIndex) {
        return sub(string, 0, toIndex);
    }

    /**
     * 切割指定位置之后部分的字符串
     *
     * @param string 字符串
     * @param fromIndex 切割开始的位置（包括）
     * @return 切割后后剩余的后半部分字符串
     */
    public static String subSuf(CharSequence string, int fromIndex) {
        if (isEmpty(string)) {
            return null;
        }

        return sub(string, fromIndex, string.length());
    }

    //byte()方法:原生的String.getByte()方法
    public static void main(String[] args)throws UnsupportedEncodingException {
        String chs = "中";
//        byte[] b_default = chs.getBytes();//文件编码方式
//        byte[] b_gbk = chs.getBytes("gbk");
//        byte[] b_utf8 = chs.getBytes("utf-8");
//        byte[] b_iso88591 = chs.getBytes("iso8859-1");
////        System.out.println(b_default.length);3
////        System.out.println(b_gbk.length);2
////        System.out.println(b_utf8.length);3
////        System.out.println(b_iso88591.length);1
//        //还原
//        String s_default = new String(b_default);
//        String s_gbk = new String(b_gbk,"gbk");
//        String s_utf8 = new String(b_utf8,"utf8");
//        String s_iso88591 = new String(b_iso88591,"iso8859-1");
////        System.out.println(s_default);
////        System.out.println(s_gbk);
////        System.out.println(s_utf8);
////        System.out.println(s_default);
////        System.out.println(s_iso88591);
//
//        String iso88591 = new String(chs.getBytes("utf8"),"iso8859-1");
//        System.out.println(iso88591);
//
//        String result = CharsetUtil.convert(iso88591,Charset.forName("iso8859-1"),Charset.forName("utf8"));
//        System.out.println(result);

        System.out.println(str((Object) chs.getBytes("utf8"), StandardCharsets.UTF_8));


    }

    /**
     * 编码字符串，编码为UTF-8
     *
     * @param str 字符串
     * @return
     */
    public static byte[] utf8Bytes(CharSequence str) {
        return bytes(str, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 编码
     * 系统默认编码
     * @param str 字符串
     * @return
     */
    public static byte[] bytes(CharSequence str){
        return bytes(str,Charset.defaultCharset());
    }

    /**
     * 编码
     *
     * @param str 字符串
     * @param charsetName 字符集名称，如果为空(blank)，则解码的结果取决于平台
     * @return
     */
    public static byte[] bytes(CharSequence str, String charsetName){
        return bytes(str,isBlank(charsetName)?Charset.defaultCharset():Charset.forName(charsetName));
    }


    /**
     * 编码
     *
     * @param str 字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return
     */
    public static byte[] bytes(CharSequence str,Charset charset){
        if(null==str){
            return null;
        }
        return null==charset?str.toString().getBytes():str.toString().getBytes(charset);
    }

    /**
     * 字符串转换为ByteBuffer
     * @param str
     * @param charset
     * @return
     */
    public static ByteBuffer byteBuffer(CharSequence str,Charset charset){
        return ByteBuffer.wrap(bytes(str,charset));
    }

    //str 将xx变成字符串

    /**
     * 对象转换为字符串，字符集为UTF-8
     *
     * @param obj
     * @return
     */
    public static String utf8Str(Object obj) {
        return str(obj, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 将对象转为字符串<br>
     * Byte数组和ByteBuffer会被转换为对应字符串
     * @param obj
     * @param charsetName
     * @return
     */
    public static String str(Object obj,String charsetName){
        return str(obj,Charset.forName(charsetName));
    }


    /**
     * 将对象转为字符串<br>
     * Byte数组和ByteBuffer会被转换为对应字符串
     * @param obj
     * @param charset
     * @return
     */
    public static String str(Object obj,Charset charset){
        if(null==obj){
            return null;
        }
        if(obj instanceof String){
            return (String)obj;
        }else if(obj instanceof byte[]){
            return str((byte[])obj,charset);
        }else if(obj instanceof Byte[]){
            return str((Byte[])obj,charset);
        }else if(obj instanceof ByteBuffer){
            return str((ByteBuffer)obj,charset);
        }else if(ArrayUtil.isArray(obj)){
            return Arrays.toString((Object[])obj);
        }
        return obj.toString();
    }

    /**
     * 解码字节码
     * @param data
     * @param charset
     * @return
     */
    public static String str(Byte[] data,Charset charset){
        if(null == data){
            return null;
        }
        byte[] bytes = new byte[data.length];
        for(int i=0;i<data.length;i++){
            bytes[i] = (data[i]==null)?-1:data[i];
        }
        return str(bytes,charset);
    }


    /**
     * 使用字符集 解码 字节码
     * @param data
     * @param charset
     * @return
     */
    public static String str(byte[] data,Charset charset){
        if(null == data){
            return null;
        }
        if(null == charset){
            return new String(data);
        }
        return new String(data,charset);

    }


    /**
     *  限定编码<strong>字符集名称</strong>，将ByteBuffer 输出 String
     * @param data  数据
     * @param charsetName 字符集
     * @tips 字符集为null,将使用 defaultCharset
     * @return
     */
    public static String str(ByteBuffer data,String charsetName){
        if(null==data){
            return null;
        }
        return str(data,Charset.forName(charsetName));
    }

    /**
     *  限定编码<strong>字符集</strong>，将ByteBuffer 输出 String
     * @param data  数据
     * @param charset 字符集
     * @tips 字符集为null,将使用 defaultCharset
     * @return
     */
    public static String str(ByteBuffer data,Charset charset){
        if(null==charset){
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }




    /**
     * {@link CharSequence} 转为字符串，null安全
     * @param str
     * @return
     */
    public static String str(CharSequence str){
        return null==str?null:str.toString();
    }



    //驼峰下划线相互转换
    /**
     * 将驼峰式命名的字符串转换为使用符号连接方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。<br>
     *
     * @param str 转换前的驼峰式命名的字符串，也可以为符号连接形式
     * @param symbol 连接符
     * @return 转换后符号连接方式命名的字符串
     */
    public static String toSymbolCase(CharSequence str, char symbol) {
        if (str == null) {
            return null;
        }

        final int length = str.length();
        final StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < length; i++) {
            c = str.charAt(i);
            final Character preChar = (i > 0) ? str.charAt(i - 1) : null;
            if (Character.isUpperCase(c)) {
                // 遇到大写字母处理
                final Character nextChar = (i < str.length() - 1) ? str.charAt(i + 1) : null;
                if (null != preChar && Character.isUpperCase(preChar)) {
                    // 前一个字符为大写，则按照一个词对待
                    sb.append(c);
                } else if (null != nextChar && Character.isUpperCase(nextChar)) {
                    // 后一个为大写字母，按照一个词对待
                    if (null != preChar && symbol != preChar) {
                        // 前一个是非大写时按照新词对待，加连接符
                        sb.append(symbol);
                    }
                    sb.append(c);
                } else {
                    // 前后都为非大写按照新词对待
                    if (null != preChar && symbol != preChar) {
                        // 前一个非连接符，补充连接符
                        sb.append(symbol);
                    }
                    sb.append(Character.toLowerCase(c));
                }
            } else {
                if (sb.length() > 0 && Character.isUpperCase(sb.charAt(sb.length() - 1)) && symbol != c) {
                    // 当结果中前一个字母为大写，当前为小写，说明此字符为新词开始（连接符也表示新词）
                    sb.append(symbol);
                }
                // 小写或符号
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将下划线方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。<br>
     * 例如：hello_world=》helloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String toCamelCase(CharSequence name) {
        if (null == name) {
            return null;
        }

        String name2 = name.toString();
        if (name2.contains(UNDERLINE)) {
            final StringBuilder sb = new StringBuilder(name2.length());
            boolean upperCase = false;
            for (int i = 0; i < name2.length(); i++) {
                char c = name2.charAt(i);

                if (c == CharUtil.UNDERLINE) {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            return sb.toString();
        } else {
            return name2;
        }
    }

    /**
     *
     * 格式化输出
     * 通常使用：format("我 {} 你", "爱") =》 我爱你<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     * @param template  字符串模版
     * @param params  依次调用toString()
     * @return
     */
    public static String format(String template,Object...params){
        if (null == template) {
            return null;
        }
        if (ArrayUtil.isEmpty(params) || isBlank(template)) {
            return template.toString();
        }
        return StrFormatter.format(template.toString(), params);
    }


    /**
     * 比较两个字符串是否相等。
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @param ignoreCase 是否忽略大小写
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为null才判断相等
            return str2 == null;
        }
        if (null == str2) {
            // 字符串2空，字符串1非空，直接false
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.equals(str2);
        }
    }


    /**
     * 比较两个字符串是否相等。
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return equals(str1,str2,true);
    }


    /**
     * 字符串为null,变成"" (empty)
     * @param str
     * @return
     */
    public static String nullToEmpty(CharSequence str) {
        return nullToDefault(str,StrUtil.EMPTY);
    }

    /**
     * 如果字符串是null，则返回指定字符串，否则返回字符串本身。
     *
     * <pre>
     * nullToDefault(null, &quot;default&quot;)  = &quot;default&quot;
     * nullToDefault(&quot;&quot;, &quot;default&quot;)    = &quot;&quot;
     * nullToDefault(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;
     * nullToDefault(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的字符串
     */
    public static String nullToDefault(CharSequence str, String defaultStr) {
        return (str == null) ? defaultStr : str.toString();
    }


    /**
     * 如果str 不以 后缀结尾，添加后缀并返回
     * @param str
     * @param suffix
     * @param ignoreCase
     * @return
     */
    public static String contactIfNotEndWithSuffix(CharSequence str, String suffix,boolean ignoreCase){
        if(isEmpty(str)||isEmpty(suffix)){
            return str(str);
        }
        final String targetStr = str.toString();
        final String suffixStr = suffix.toString();
        if (ignoreCase){
            return targetStr.toLowerCase().endsWith(suffix.toLowerCase())?targetStr:targetStr.concat(suffixStr);
        }else {
            return targetStr.endsWith(suffix)?targetStr:targetStr.concat(suffixStr);
        }
    }


    //contains
    /**
     * 查找指定字符串是否包含指定字符数组中的任意一个字符
     *
     * @param str 指定字符串
     * @param testChars 需要检查的字符数组
     * @return 是否包含任意一个字符
     */
    public static boolean containsAny(CharSequence str, char... testChars) {
        if (!isEmpty(str)) {
            int len = str.length();
            for (int i = 0; i < len; i++) {
                if (ArrayUtil.contains(testChars, str.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}

