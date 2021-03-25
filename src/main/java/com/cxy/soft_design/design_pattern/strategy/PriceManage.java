package com.cxy.soft_design.design_pattern.strategy;

/**
 * Description:  价格管理，主要完成计算向客户所报价格的功能  <br>
 * Date: 2020/6/24 11:20  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class PriceManage {

    /**
     * 策略对象
     */
    private Strategy strategy = null;
    /**
     * 注入具体的策略对象
     * @param aStrategy 具体的策略对象
     */
    public PriceManage(Strategy aStrategy){
        this.strategy = aStrategy;
    }

    /**
     * 计算对客户的报价
     * @param goodsPrice 原价
     * @return 报价
     */
    public double quote(double goodsPrice){
        return this.strategy.calcPrice(goodsPrice);
    }
}
