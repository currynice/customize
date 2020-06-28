package com.cxy.design_pattern.strategy;

/**
 * Description: Vip客户报价  <br>
 * Date: 2020/6/24 11:17  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class VipCustomerStrategy implements Strategy{
    public double calcPrice(double goodsPrice) {
        System.out.println("对于vip，统一折扣10%");
        return goodsPrice*(1-0.1);
    }
}