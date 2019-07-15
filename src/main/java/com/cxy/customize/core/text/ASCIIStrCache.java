package com.cxy.customize.core.text;


/**
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
     * 如果为ASCII字符，从缓存中取，避免不必要的String对象
     * @param c 字符
     * @return 字符串
     */
    public static String toString(char c) {
        return c < ASCII_LENGTH ? CACHE[c] : String.valueOf(c);
    }
}
