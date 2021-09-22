package com.cxy.customize.concurrent;

/**
 * Description:   </br>
 * Date: 2021/9/18 16:53
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Account2 {

    // 锁：保护账户余额
    private  Object lock;

    // 账户余额
    private Integer balance;

    private Account2(){

    }

    public Account2(Object lock, Integer balance) {
        this.lock = lock;
        this.balance = balance;
    }

    // 转账
    void transfer(Account2 target, int amt){
        synchronized (lock){

            if (this.balance > amt) {
                 this.balance -= amt;
                target.balance += amt;
            }
         }
    }

}

