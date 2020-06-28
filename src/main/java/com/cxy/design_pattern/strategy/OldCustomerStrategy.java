package com.cxy.design_pattern.strategy;

/**
 * Description:老客户报价   <br>
 * Date: 2020/6/24 11:17  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class OldCustomerStrategy implements Strategy{
    public double calcPrice(double goodsPrice) {
        System.out.println("对于老客户，统一折扣5%");
        return goodsPrice*(1-0.05);
    }

}