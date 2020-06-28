package com.cxy.design_pattern.strategy;

/**
 * Description: 新客或普通客户报价  <br>
 * Date: 2020/6/24 11:17  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
/**
 * 具体算法实现，为新客户或者是普通客户计算应报的价格
 */
public class NormalCustomerStrategy implements Strategy{
    public double calcPrice(double goodsPrice) {
        System.out.println("新客或普通客户报价，没有折扣");
        return goodsPrice;
    }
}
