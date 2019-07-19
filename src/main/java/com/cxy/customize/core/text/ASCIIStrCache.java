package com.cxy.customize.core.text;


/**
 * ASCII一共128个 包括空格、标点符号、数字、大小写字母和一些不可见字符。由于总共才128个字符，所以可以使用1个字节来进行编码
 * 维护了一个ASCII字符对应的字符串缓存
 */
public class ASCIIStrCache {
    private static final int ASCII_LENGTH = 128;
    private static final String[] CACHE = new String[ASCII_LENGTH];

    static {
        for(int i=0;i<ASCII_LENGTH;i++){
            CACHE[i] = String.valueOf(i);
        }
    }

    /**
     * 字符转为字符串<br>
     * 如果为ASCII字符，从缓存中取，避免创建不必要的String对象
     * @param c 字符
     * @return 字符串
     */
    public static String toString(char c) {
        return c < ASCII_LENGTH ? CACHE[c] : String.valueOf(c);
    }
}
