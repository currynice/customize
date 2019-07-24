package com.cxy.customize.core.util;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

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



    //..........removeXXX 去除前缀后缀

    /**
     * {@link CharSequence} 转为字符串，null安全
     * @param str
     * @return
     */
    public static String str(CharSequence str){
        return null==str?null:str.toString();
    }


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
        String iso88591 = new String(chs.getBytes("utf8"),"iso8859-1");
        System.out.println(iso88591);

        String result = CharsetUtil.convert(iso88591,Charset.forName("iso8859-1"),Charset.forName("utf8"));
        System.out.println(result);


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









}
