package com.cxy.customize.concurrent;

/**
 * Description:   </br>
 * Date: 2021/9/18 16:53
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Account3 {



    // 账户余额
    private Integer balance;



    // 转账
    void transfer(Account3 target, int amt){
        synchronized (Account3.class){

            if (this.balance > amt) {
                 this.balance -= amt;
                target.balance += amt;
            }
         }
    }

}

