package com.cxy.customize.core.util;


import cn.hutool.core.util.StrUtil;
import com.cxy.customize.core.exceptions.ToDigitException;

import java.nio.charset.Charset;

/**
 * 16进制工具类
 * 十六进制（简写为hex或下标16）在数学中是一种逢16进1的进位制，一般用数字0到9和字母A到F表示（其中:A~F即10~15）
 * 0x20 16进制的20,即十进制的32
 * 一般都是byte[]数组，字符串与16进制之间的转换
 * java中16进制出现的地方:
 * 1.读取图片等文件的16进制，用于判断文件的真实类型
 * 2.加密
 * 3.进制转换
 */
public class HexUtil {
    //16进制 小写字符数组
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    //16进制 大写字符数组
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };


    /**
     * 判断字符串值是否是合法的16进制格式
     * @param value
     * @return
     */
    public static boolean isHexNumber(String value){
        final int startIndex = value.startsWith("-")?1:0;
        if(value.startsWith("Ox",startIndex)||value.startsWith("ox",startIndex)||value.startsWith("#",startIndex)){
            try{
                Long.decode(value);
            }catch (NumberFormatException e){
                return false;
            }
            return true;
        }else {
            return false;
        }

    }
    // ---------------------------------------------------------------------------------------------------- encode
    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data byte[]
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param str 字符串
     * @param charset 编码
     * @return 十六进制char[]
     */
    public static char[] encodeHex(String str, Charset charset) {
        return encodeHex(StrUtil.bytes(str, charset), true);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data byte[]
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data byte[]
     * @return 十六进制String
     */
    public static String encodeHexStr(byte[] data) {
        return encodeHexStr(data, true);
    }

    /**
     * 将字节数组转换为十六进制字符串，结果为小写
     *
     * @param data 被编码的字符串
     * @param charset 编码
     * @return 十六进制String
     */
    public static String encodeHexStr(String data, Charset charset) {
        return encodeHexStr(StrUtil.bytes(data, charset), true);
    }

    /**
     * 将字节数组转换为十六进制字符串，结果为小写，默认编码是UTF-8
     *
     * @param data 被编码的字符串
     * @return 十六进制String
     */
    public static String encodeHexStr(String data) {
        return encodeHexStr(data, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data byte[]
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制String
     */
    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    // ---------------------------------------------------------------------------------------------------- decode
    /**
     * 将十六进制字符数组转换为字符串，默认编码UTF-8
     *
     * @param hexStr 十六进制String
     * @return 字符串
     */
    public static String decodeHexStr(String hexStr) {
        return decodeHexStr(hexStr, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 将十六进制字符数组转换为字符串
     *
     * @param hexStr 十六进制String
     * @param charset 编码
     * @return 字符串
     */
    public static String decodeHexStr(String hexStr, Charset charset) {
        if (StrUtil.isEmpty(hexStr)) {
            return hexStr;
        }
        return decodeHexStr(hexStr.toCharArray(), charset);
    }

    /**
     * 将十六进制字符数组转换为字符串
     *
     * @param hexData 十六进制char[]
     * @param charset 编码
     * @return 字符串
     */
    public static String decodeHexStr(char[] hexData, Charset charset) {
        return StrUtil.str(decodeHex(hexData), charset);
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param hexData 十六进制char[]
     * @return byte[]
     * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] decodeHex(char[] hexData) {

        int len = hexData.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(hexData[j], j) << 4;
            j++;
            f = f | toDigit(hexData[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * 将十六进制字符串解码为byte[]
     *
     * @param hexStr 十六进制String
     * @return byte[]
     */
    public static byte[] decodeHex(String hexStr) {
        if (StrUtil.isEmpty(hexStr)) {
            return null;
        }
        return decodeHex(hexStr.toCharArray());
    }

    /**
     * 将指定int值转换为Unicode字符串形式，常用于特殊字符（例如汉字）转Unicode形式<br>
     * 转换的字符串如果u后不足4位，则前面用0填充，例如：
     *
     * <pre>
     * '我' =》\u4f60
     * </pre>
     *
     * @param value int值，也可以是char
     * @return Unicode表现形式
     */
    public static String toUnicodeHex(int value) {
        final StringBuilder builder = new StringBuilder(6);

        builder.append("\\u");
        String hex = toHex(value);
        int len = hex.length();
        if (len < 4) {
            builder.append("0000", 0, 4 - len);// 不足4位补0
        }
        builder.append(hex);

        return builder.toString();
    }

    /**
     * 将指定char值转换为Unicode字符串形式，常用于特殊字符（例如汉字）转Unicode形式<br>
     * 转换的字符串如果u后不足4位，则前面用0填充，例如：
     *
     * <pre>
     * '我' =》\u4f60
     * </pre>
     *
     * @param ch char值
     * @return Unicode表现形式
     * @since 4.0.1
     */
    public static String toUnicodeHex(char ch) {
        StringBuilder sb = new StringBuilder(6);
        sb.append("\\u");
        sb.append(DIGITS_LOWER[(ch >> 12) & 15]);
        sb.append(DIGITS_LOWER[(ch >> 8) & 15]);
        sb.append(DIGITS_LOWER[(ch >> 4) & 15]);
        sb.append(DIGITS_LOWER[(ch) & 15]);
        return sb.toString();
    }
    /**
     * 转为16进制字符串
     *
     * @param value int值
     * @return 16进制字符串
     * @since 4.4.1
     */
    public static String toHex(int value) {
        return Integer.toHexString(value);
    }

    /**
     * 转为16进制字符串
     *
     * @param value int值
     * @return 16进制字符串
     * @since 4.4.1
     */
    public static String toHex(long value) {
        return Long.toHexString(value);
    }
    //....

    /**
     * 将byte值转为16进制并添加到{@link StringBuilder}中
     * @param builder {@link StringBuilder}
     * @param b byte
     * @param toLowerCase 是否使用小写
     * @since 4.4.1
     */
    public static void appendHex(StringBuilder builder, byte b, boolean toLowerCase) {
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;

        int high = (b & 0xf0) >>> 4;//高位
        int low = b & 0x0f;//低位
        builder.append(toDigits[high]);
        builder.append(toDigits[low]);
    }

//---------------private method

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制String
     */
    private static String encodeHexStr(byte[] data, char[] toDigits){
         return new String(encodeHex(data,toDigits));
    }


    /**
     * 将字节数组转换为十六进制字符数组 todo 算法检查
     *
     * @param data byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制char[]
     */
    private static char[] encodeHex(byte[] data, char[] toDigits) {
        final int len = data.length;
        final char[] out = new char[len << 1];//len*2
        // two characters from the hex value.
        for (int i = 0, j = 0; i < len; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];// 高位
            out[j++] = toDigits[0x0F & data[i]];// 低位
        }
        return out;
    }

    /**
     *  字符转为对应的16进制数字，转换失败将抛出自定义的RuntimeException异常
     * @param ch     字符
     * @param index  在16进制char[] 中的index
     * @return
     */
    private static int toDigit(char ch,int index){
        //字符转换为16进制数字
        int digit = Character.digit(ch,16);
        if(-1 == digit){
            throw  new ToDigitException(StrUtil.format("index为{}的{}不是合法的16进制数",index,ch));
        }
        return digit;
    }

    public static void main(String[] args) {
        System.out.println(toDigit('f',11));
        System.out.println(toDigit('F',11));
    }

}
