package com.cxy.customize.core.util;


import com.cxy.customize.core.lang.Assert;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 随机工具类   https://www.xttblog.com/?p=2598  
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
     * 获取随机数生成器对象<br>
     * JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。不能setSeed
     *
     * @return {@link ThreadLocalRandom}
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获得增强性的随机数生成器
     * @return
     */
    public static SecureRandom getSecureRandom(){
        try {
            return SecureRandom.getInstance("SHA1PRNG","SUN");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param seed  种子数，根据这个得到
     * @param num 返回的list个数
     * @param bound [0-bound) 随机数边界限制  num需要小于bound
     * @return
     */
    public static List<Integer> getIntegerList2(long seed,int num,int bound){
        //考虑num大于bound的情况，比如 seed=1,5个[0,3)的随机数，会是这样的情况，[0, 1, 1, 0, 2],导致重复，所以，这样的情况先返回
        Assert.isTrue(num<bound);
        SortedSet<Integer> numberSet = Collections.synchronizedSortedSet(new TreeSet());
          List<Integer> list = new Random(seed).ints(num,0,bound).boxed().collect(Collectors.toList());
            //添加失败，说明有重复的
            if(!numberSet.addAll(list)){
                return Collections.emptyList();
            }
        //排序后输出比较好
        return new ArrayList<>(numberSet);
    }


    public static void main(String[] args){

        System.out.println(getIntegerList2(1L,10,500));

    }
}
