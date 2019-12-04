package com.cxy.customize.core.util;


import com.cxy.customize.core.date.DateField;
import com.cxy.customize.core.exceptions.UtilException;
import com.cxy.customize.core.lang.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.cxy.customize.core.util.NumberUtil.*;

/**
 * 随机工具类
 *
 * @author cxy
 *
 */
public class RandomUtil {
    /** 用于随机选的数字 */
    public static final String BASE_NUMBER = "0123456789";
    /** 用于随机选的字符 */
    public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    /** 用于随机选的字符和数字 */
    public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

    /**
     * 获得随机数生成器对象
     * @param isSecure  是否强随机数
     */
    public static Random getRandom(boolean isSecure){
        return isSecure?getSecureRandom():getRandom();

    }

    /**
     * 获取随机数生成器对象<br>
     * JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。不能setSeed
     *
     * @return {@link ThreadLocalRandom}
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     *  获得增强型的随机数生成器（new SecureRandom得到NativePRNG算法的生成器）
     * @return
     */
    public static SecureRandom getSecureRandom(){
        try {
            return SecureRandom.getInstance("SHA1PRNG","SUN");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
           throw new UtilException(e);
        }
    }


    //————————————
    /**
     * 获得指定范围内的随机数 [0,limit)
     *@param min 限制随机数的范围下限，包括这个数
     * @param max 限制随机数的范围上限，不包括这个数
     * @return 随机数
     */
    public static int randomInt(int min,int max) {
        return getRandom().nextInt(min,max);
    }

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static int randomInt(int limit) {
        return getRandom().nextInt(limit);
    }

    /**
     * 获得指定范围内 [min,max) 的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @param scale 保留小数位数
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 随机数
     */
    public static double randomDouble(double min, double max, int scale, RoundingMode roundingMode) {
        return NumberUtil.round(randomDouble(min, max), scale, roundingMode).doubleValue();
    }

    public static double randomDouble(double min, double max) {
        return getRandom().nextDouble(min,max);
    }

    /**
     * [0,1)的随机 bigDecimal
     * @param min
     * @param max
     * @return
     */
    public static BigDecimal randomBigDecimasl(double min, double max) {
        return toBigDecimal(getRandom().nextDouble(min,max));
    }

    /**
     * 获得随机byte数组,长度length
     * @param length 返回的数组长度
     * @return
     */
    public static byte[] randomBytes(int length){
        byte[] bytes = new byte[length];
        getRandom().nextBytes(bytes);//void 不能直接返回
        return bytes;
    }

    //——————————

    /**
     * 获得列表前N项的任意一个元素(随机取的index)
     * @param list
     * @param limit 列表前几项   [0,limit)所以不要-1
     * @return
     */
    public static <T>T randomElement(List<T> list,int limit){
        return list.get(randomInt(limit));
    }

    /**
     * 获得列表中的任意一个元素
     * @param list
     * @return
     */
    public static <T>T randomElement(List<T> list){
        return list.get(randomInt(list.size()));
    }

//——————————
    /**
     * 获得数组前N项的任意一个元素(随机取的index)
     * @param array
     * @param limit 列表前几项   [0,limit)所以不要-1
     * @return
     */
    public static <T>T randomElement(T[] array,int limit){
        return array[randomInt(limit)];
    }

    /**
     * 获得数组中的任意一个元素
     * @param array
     * @return
     */
    public static <T>T randomElement(T[] array){
        return array[randomInt(array.length)];
    }


    //——————————
    /**
     * 随机获得列表中的一定量元素
     *
     * @param <T> 元素类型
     * @param list 列表
     * @param count 随机取出的个数
     * @return 随机元素
     */
    public static <T> List<T> randomEles(List<T> list, int count) {
        final List<T> result = new ArrayList<>(count);
        int limit = list.size();
        while (result.size() < count) {
            result.add(randomElement(list, limit));
        }

        return result;
    }

    /**
     * 随机获得列表中的一定量的不重复元素，返回Set
     *
     * @param <T> 元素类型
     * @param collection 列表
     * @param count 随机取出的个数
     * @return 随机元素
     * @throws IllegalArgumentException 需要的长度大于给定集合非重复总数
     */
    public static <T> Set<T> randomEleSet(Collection<T> collection, int count) {
        ArrayList<T> source = new ArrayList<>(new HashSet<>(collection));
        if (count > source.size()) {
            throw new IllegalArgumentException("Count is larger than collection distinct size !");
        }

        final HashSet<T> result = new HashSet<>(count);
        int limit = collection.size();
        while (result.size() < count) {
            result.add(randomElement(source, limit));
        }

        return result;
    }


    /**
     * 指定随机时间字段和偏移范围，随机出某一日期。
     * @param date
     * @param field
     * @param min
     * @param max
     * @return
     */
    public static LocalDateTime randomLocalDate(Date date, DateField field,int min,int max){
        return DateUtil.offset(date,field,randomInt(min,max));

    }



    /**
     * 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，总体而言 ：
     * 50%的概率返回 1
     * 25%的概率返回 2
     * 12.5%的概率返回 3 ...
     * @param MAX_LEVEL
     * @return
     */
    private static int randomLevel(int MAX_LEVEL) {

        int level = 1;

        while (Math.random() < 0.5f && level < MAX_LEVEL)
            level += 1;
        return level;
    }







    /**
     *
     * @param seed  种子数，根据这个得到  num个随机数列表
     * @param num 返回的list个数
     * @param bound [0-bound) 随机数边界限制  num需要小于bound
     * @return
     */
    public static List<Integer> getIntegerList2(long seed,int num,int bound){
        //考虑num大于bound的情况，比如 seed=1,5个[0,3)的随机数，会是这样的情况，[0, 1, 1, 0, 2],导致重复，所以，这样的情况先返回
        Assert.isTrue(num<bound,"warn!!!总数要小于边界");
        SortedSet<Integer> numberSet = Collections.synchronizedSortedSet(new TreeSet<>());
          List<Integer> list = new Random(seed).ints(num,0,bound).boxed().collect(Collectors.toList());
            //添加失败，说明有重复的
            if(!numberSet.addAll(list)||num!=numberSet.size()){
              throw new UtilException("随机获取失败");
            }
        //排序后输出比较好
        return new ArrayList<>(numberSet);
    }


    public static void main(String[] args){


        System.out.println(randomLocalDate(new Date(),DateField.HOUR,-5,5));
    }
}
