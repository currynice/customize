package com.cxy.customize.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Description:   </br>
 * Date: 2021/9/24 10:47
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class MyMutex  implements Lock {


    // 聚合⾃定义同步器
    private final MySync sync = new MySync();


    @Override
    public void lock() {
        // 阻塞获取锁，调⽤同步器模版⽅法<独占式获取同步状态>
        sync.acquire(1);
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 调⽤同步器模版⽅法可中断式获取同步状态
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        // 调⽤⾃⼰重写的⽅法，⾮阻塞式的获取同步状态
        return sync.tryAcquire(1);
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // 调⽤同步器模版⽅法，可响应中断和超时时间限制
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
    @Override
    public void unlock() {
        // 释放锁
        sync.release(1);
    }
    @Override
    public Condition newCondition() {
        // 使⽤⾃定义的条件
        return sync.newCondition();
    }


    /**
     * 自定义同步器
     */
    private static class MySync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            // 调⽤AQS提供的⽅法，通过CAS保证原⼦性
            if (compareAndSetState(0, arg)){
                // 实现的是互斥锁，所以标记获取到同步状态（更新state成功）的线程是当前线程，
                setExclusiveOwnerThread(Thread.currentThread());
                //获取同步状态成功，返回 true
                return true;
            }
            // 获取同步状态失败，返回false
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 未拥有锁却执行释放，会抛出 IMSE
            if (getState() == 0){
                throw new IllegalMonitorStateException();
            }
            // 先清空排它线程标记
            setExclusiveOwnerThread(null);
            // 设置同步状态为0，释放锁
            setState(0);
            return true;
        }


        // 是否独占式:是
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }


        Condition newCondition() {
            return new ConditionObject();
        }
    }

}
