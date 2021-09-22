//package com.cxy.customize.concurrent.juc;
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * Description:   </br>
// * Date: 2021/9/22 17:20
// *
// * @author :cxy </br>
// * @version : 1.0 </br>
// */
//public class SimpleBlockingQueue<T> {
//
//    //锁
//    final Lock lock = new ReentrantLock();
//    // 条件：队列不满
//    final Condition notFull = lock.newCondition();
//    // 条件：队列不空
//    final Condition notEmpty = lock.newCondition();
//    // ⼊队
//
//
//    public void enqueue(T x) {
//        lock.lock();
//        try {
//            while (队列已满){
//                // 等待直到队列不满
//                notFull.await();
//            }
//            // ⼊队操作...
//            //⼊队后通知可出队一个
//            notEmpty.signal(); //notify
//        }finally {
//            lock.unlock();
//        }
//    }
//
//
//    // 出队
//    public void dequeue(){
//        lock.lock();
//        try {
//            while (队列已空){
//                // 等待直到队列不空
//                notEmpty.await();
//            }
//            // 出队操作...
//            // 出队后，通知等待入队的线程⼊队
//            notFull.signal();
//        }finally {
//            lock.unlock();
//        }
//    }
//}
