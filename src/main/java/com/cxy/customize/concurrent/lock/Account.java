package com.cxy.customize.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: todo   </br>
 * Date: 2021/9/26 9:51
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Account {

    private int balance;
    private final Lock lock = new ReentrantLock();

    // 转账
    void transfer(Account tar, int amt){
        while (true) {
            if(this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer

}

