package com.cxy.customize.concurrent.problems.visibility;


public class SafeCalrTest {
    long count = 0;


    private synchronized  long getCount(){
        return count;

    }


    private  synchronized void calr() {
        int index = 0;
        while(index++ < 10000){
            count+=1;
        }

    }

    public static void main(String args[]){

        try {
            SafeCalrTest test = new SafeCalrTest();
            Thread thread1 = new Thread(()->
                    test.calr());

            Thread thread2 = new Thread(()->
                  System.out.println(test.getCount()));
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
