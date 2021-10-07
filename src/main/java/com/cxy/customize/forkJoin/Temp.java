package com.cxy.customize.forkJoin;

public class Temp {


    public static void main(String[] args) {
        System.out.println(ss(10));
        System.out.println(ss(2));
        System.out.println((1<<13)/1024);
    }

    private static boolean ss(int a){
        return Math.min(a,0xfff) == (a&0xfff);
    }


}
