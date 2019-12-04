package com.cxy.customize.core.lang;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义断言<br>
 * 断言某些对象或值是否符合规定，否则抛出异常。主要用于做变量检查
 * 解决了程序退出的问题
 * @author Looly
 *
 */
public class Assert {


    /**
     * 是否True
     * @param target 比较对象
     * @param errorMsgTemplate 输出模版，变量使用{}代替
     * @param params   参数
     */
    public static void isTrue(boolean target,String errorMsgTemplate,Object ...params){
        if(!target){
            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate,params));
        }
    }
    /**
     * 是否True
     * @param target 比较对象
     */
    public static void isTrue(boolean target){
       isTrue(target,"[Assert failed]--this value must be true");
    }


    /**
     * 断言是否为假，如果为  true 抛出 IllegalArgumentException <br>
     * @param target
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params 参数列表
     * @throws IllegalArgumentException
     */
    public static void isFalse(boolean target, String errorMsgTemplate, Object... params)  {
        if (target) {
            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    /**
     * 断言是否为假
     */
    public static void isFalse(boolean expression)  {
        isFalse(expression, "[Assert failed] - this  must be false");
    }

    /**
     * 是否为 null
     * @param target 比较对象
     * @param template 输出模版，变量使用{}代替
     * @param params   参数
     */
    public static void isNull(Object target,String template,Object ...params){
        if(null!=target){
            throw new IllegalArgumentException(StrUtil.format(template,params));
        }
    }
    /**
     * 是否为 null
     * @param target 比较对象
     */
    public static void isNull(Object target) {
        isNull(target,"[Assert failed]--{} must be null",target);
    }


    /**
     * 是否为 非null
     * @param target 比较对象
     * @param template 输出模版，变量使用{}代替
     * @param params   参数
    * @return 被检查后的字符串
     */
    public static <T>T notNull(T target,String template,Object ...params){
        if(null==target){
            throw new IllegalArgumentException(StrUtil.format(template,params));
        }
        return target;
    }
    /**
     * 是否为 非null
     */
    public static <T>T notNull(T target){
       return notNull(target,"[Assert Fail]--{} must be notNull",target);
    }

    /**
     * 字符串是否为 非empty
     * @param text 比较字符串
     * @param errorMsgTemplate 输出模版，变量使用{}代替
     * @param params   参数
     * @return 被检查后的字符串
     */
    public static String notEmpty(String text, String errorMsgTemplate, Object... params){
        if (StrUtil.isEmpty(text)) {
            throw new IllegalArgumentException(cn.hutool.core.util.StrUtil.format(errorMsgTemplate, params));
        }
        return text;
    }

    /**
     * 字符串是否为 非empty
     */
    public static String notEmpty(String text) {

        return notEmpty(text,"[Assert Fail]--{} must be notEmpty");
    }


    /**
     * 字符串是否为 非blank
     * @param text 比较字符串
     * @param errorMsgTemplate 输出模版，变量使用{}代替
     * @param params   参数
     * @return 被检查后的字符串
     */
    public static String notBlank(String text, String errorMsgTemplate, Object... params)  {
        if (StrUtil.isBlank(text)) {
            throw new IllegalArgumentException(cn.hutool.core.util.StrUtil.format(errorMsgTemplate, params));
        }
        return text;
    }

    /**
     * 字符串是否为 非blank
     */
    public static String notBlank(String text)  {

        return notBlank(text,"[Assert Fail]--{} must be notBlank");
    }

    /**
     * noNullElements 数组中是否包含null元素
     * isInstanceOf 是否类实例
     * isAssignable 是子类和父类关系
     * state 会抛出IllegalStateException异常
     */

    /**
     * 字符串是否为 父串的 子串,非子串抛异常
     * @param text 进行断言的字符串
     * @parm  search   父串
     * @param errorMsgTemplate 输出模版，变量使用{}代替
     * @param params   参数
     * @return 被检查后的字符串
     */
    public static String contain(String text,String search,String errorMsgTemplate, Object... params){
        //非子串
        if (StrUtil.isNotBlank(text)&&StrUtil.isNotEmpty(text)&&!search.contains(text)) {
            throw new IllegalArgumentException(cn.hutool.core.util.StrUtil.format(errorMsgTemplate, params));
        }
        return text;
    }

    /**
     * 字符串是否为 父串的 子串,非子串抛异常
     */
    public static String contain(String text,String search){
        //非子串
        return contain(text,search,"[Assert failed] {} must contain {}",search,text);
    }

    /**
     * 断言给定对象是否是给定类的实例
     */
    public static <T> T isInstanceOf(Class<?> type, T obj) {
        return isInstanceOf(type, obj, "Object [{}] is not instanceof [{}]", obj, type);
    }

    /**
     * 断言给定对象是否是给定类的实例
     *
     * @param <T> 被检查对象泛型类型
     * @param type 待匹配的类型
     * @param obj 待检查对象
     * @return  检查后对象
     * @throws IllegalArgumentException if the object is not an instance of clazz
     */
    public static <T> T isInstanceOf(Class<?> type, T obj, String errorMsgTemplate, Object... params) {
        //断言中使用断言
        notNull(type, "Type to be checked  must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
        return obj;
    }


    //todo  superType.isAssignableFrom(subType) 子类




    //————————数值
    /**
     * 检查值是否在指定范围内
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @return
     */
    public static int checkBetween(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(StrUtil.format("Length must be between {} and {}.", min, max));
        }
        return value;
    }

    /** 一系列重载
     * Number类型利用{@link Number#doubleValue()}转为double进行比较
     */




}
