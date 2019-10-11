package com.cxy.customize.thread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {



    public static void main(String args[]){

//        System.out.println(Thread.currentThread().getName());
//        String result = CompletableFuture.supplyAsync(()->{
//       System.out.println(Thread.currentThread().getName());
//            return "hello";
//        }).thenCombine( CompletableFuture.supplyAsync(()->{
//       System.out.println(Thread.currentThread().getName());
//            return "world";
//        }),(r1,r2)->(r1+r2)).join();
//        System.out.println(result);


      String a = CompletableFuture.supplyAsync(()->Integer.parseInt("22")).
              thenApply(s -> "s:" + s).exceptionally(ex -> ex.getMessage()).join();
        System.out.println(a);//s:22

        String a2 = CompletableFuture.supplyAsync(()->Integer.parseInt("sss")).
                thenApply(s -> "s:" + s).exceptionally(ex -> ex.getMessage()).join();
        System.out.println(a2);//java.lang.NumberFormatException: For input string: "sss"



        String a3 = CompletableFuture.supplyAsync(AsyncMethod::methodWithError).thenApply(s -> "s:" + s).handle((result,e)->{
                if(result!=null){
                return result;
                }else {
                    return e.getMessage();
                }
        }).join();


        System.out.println(a3);

    }



}
