package com.cxy.design_pattern.strategy;

/**
 * Description: 策略，定义计算报价的方法  <br>
 * Date: 2020/6/24 11:16  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public interface Strategy {


    /**
     * 计算最终报格
     * @param goodsPrice 商品原价
     * @return 计算出来的报价
     */
     double calcPrice(double goodsPrice);
}
