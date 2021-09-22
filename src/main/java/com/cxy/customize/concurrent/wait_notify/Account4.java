package com.cxy.customize.concurrent.wait_notify;


/**
 * Description:   </br>
 * Date: 2021/9/18 16:53
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Account4 {

    // 账户余额
    private volatile Integer balance;

    // actr应该为单例 todo
    private Allocator actr;


    public Account4(Integer balance, Allocator actr) {
        this.balance = balance;
        this.actr = actr;
    }

    // 转账
    void transfer(Account4 target, int amt) {
        // 一次性申请转出账户和转入账户，直到成功
        while(!actr.apply(this, target)) {};


            try{
                // 锁定转出账户
                synchronized(this){
                    // 锁定转入账户
                    synchronized(target){
                        if (this.balance > amt){
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            } finally {
                actr.free(this, target);
            }

    }

    public Integer getBalance() {
        return balance;
    }
}


class TestAccount4{
    public static void main(String[] args) throws InterruptedException {
        Allocator actr = new Allocator();
        Account4 u1 = new Account4(100,actr);
        Account4 u2 = new Account4(50,actr);

        new Thread(()->u1.transfer(u2,10)).start();
        new Thread(()->u2.transfer(u1,5)).start();

        Thread.sleep(20*1000L);
        System.out.println(u1.getBalance());
        System.out.println(u2.getBalance());

    }
}

