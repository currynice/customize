package com.cxy.customize.core.util;



import com.cxy.customize.core.text.ASCIIStrCache;

import java.io.File;

/**
 * 字符工具类
 * CXY
 */
public class CharUtil {
    public static final char SPACE = ' ';
    public static final char TAB = '	';
    public static final char DOT = '.';
    public static final char SLASH = '/';
    public static final char BACKSLASH = '\\';
    public static final char CR = '\r';
    public static final char LF = '\n';
    public static final char UNDERLINE = '_';
    public static final char DASHED = '-';
    public static final char COMMA = ',';
    public static final char DELIM_START = '{';
    public static final char DELIM_END = '}';
    public static final char BRACKET_START = '[';
    public static final char BRACKET_END = ']';
    public static final char COLON = ':';
    public static final char DOUBLE_QUOTES = '"';
    public static final char SINGLE_QUOTE = '\'';
    public static final char AMP = '&';

    /**
     * 字符是否是 ASCII（0-127）
     * 参考 :http://ascii.911cha.com/
     * @param c
     * @return
     */
    public static boolean isASCII(char c){
        return c<128;
    }

    /**
     * 字符是否是 可显示ASCII（32-126）
     * 参考 :http://ascii.911cha.com/
     * @param c
     * @return
     */
    public static boolean isDisplayableASCII(char c){
        return c>=32&&c<=126;
    }

    /**
     * 字符是否是 ASCII控制字符（0-31  127）
     * 参考 :http://ascii.911cha.com/
     * @param c
     * @return
     */
    public static boolean isControlASCII(char c){
        return c<32 || c==127;
    }

    /**
     * 字符是否是 字母
     * @param c
     * @return
     */
    public static boolean isLetter(char c){
        return isLowerLetter(c) || isUpperLetter(c);
    }


    /**
     * 字符是否是 大写字母 A-Z
     * 参考 :http://ascii.911cha.com/
     * @param c
     * @return
     */
    public static boolean isUpperLetter(char c){
        return c<='Z' && c>='A';
    }

    /**
     * 字符是否是 小写字母 a-z
     * 参考 :http://ascii.911cha.com/
     * @param c
     * @return
     */
    public static boolean isLowerLetter(char c){
        return c<='z' && c>='a';
    }

    /**
     * 字符是否是 数字 a-z
     * @param c
     * @return
     */
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 字符是否是 十六进制数字
     * 0-9
     * a-f
     * A-F
     * @param c
     * @return
     */
    public static boolean isHexNumber(char c) {
        return isNumber(c) || (c<='f' && c>='a') || (c<='F' && c>='A');
    }


    /**
     * 字符是否是 字母或数字
     * @param c
     * @return
     */
    public static boolean isLetterOrNumber(final char c) {
        return isLetter(c) || isNumber(c);
    }

    /**
     * 字符转为字符串<br>
     * 如果为ASCII字符，使用缓存
     *
     * @param c 字符
     * @return 字符串
     * @see ASCIIStrCache#toString(char)
     */
    public static String toString(char c) {
        return ASCIIStrCache.toString(c);
    }


    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int 2)
     * @param c 字符
     * @return 是否空白符
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == '\ufeff' || c == '\u202a';
    }

    /**
     * 判断是否为emoji表情符<br>
     * @param c 字符
     * @return 是否为emoji
     */
    public static boolean isEmoji(char c) {
        return false ==  ((c == 0x0) || //
                (c == 0x9) || //
                (c == 0xA) || //
                (c == 0xD) || //
                ((c >= 0x20) && (c <= 0xD7FF)) || //
                ((c >= 0xE000) && (c <= 0xFFFD)) || //
                ((c >= 0x10000) && (c <= 0x10FFFF)));
    }

    /**
     * 是否为Windows或者Linux（Unix）文件分隔符<br>
     * Windows平台下分隔符为\，Linux（Unix）为/
     * (如果要获得当前操作系统的文件分隔符号可以使用
     * @see  File#separatorChar )
     * @param c 字符
     * @return
     */
    public static boolean isFileSeparator(char c) {
        return SLASH == c || BACKSLASH == c;
    }

    /**
     * 比较字符是否相等
     * @param c1
     * @param c2
     * @param ignoreCase 忽略大小写
     * @return
     */
    public static boolean equals(char c1,char c2,boolean ignoreCase){
        if(ignoreCase){
            return Character.toLowerCase(c1)==Character.toLowerCase(c2);
        }else{
            return c1==c2;
        }
    }


}
