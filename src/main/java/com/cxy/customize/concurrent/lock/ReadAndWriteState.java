package com.cxy.customize.concurrent.lock;

/**
 * Description:   </br>
 * Date: 2021/9/26 15:24
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ReadAndWriteState {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    /** Returns the number of shared holds represented in count  */
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
    /** Returns the number of exclusive holds represented in count  */
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    static int sharedAdd(int c){
        return c+SHARED_UNIT;
    }

    static int exclusiveAdd(int c){
        return c+1;
    }

    public static void main(String[] args) {

        int c=0;
        System.out.println(sharedCount(c));
        System.out.println(exclusiveCount(c));

        System.out.println("-------");

         c=sharedAdd(c);
         c=sharedAdd(c);
         c=exclusiveAdd(c);

        System.out.println(sharedCount(c));
        System.out.println(exclusiveCount(c));



    }
}
