package com.cxy.customize.core.util;



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
            //只要一个字符非空，该字符串即为空
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






}
