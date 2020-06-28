package com.cxy.design_pattern.strategy;

/**
 * Description:   <br>
 * Date: 2020/6/24 11:23  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class TestClient {

    public static void main(String[] args) {
            //1：选择并创建需要使用的策略对象
            Strategy strategy = new VipCustomerStrategy ();

            PriceManage ctx = new PriceManage(strategy);
            //3：计算报价
            double quote = ctx.quote(1000);
            System.out.println("向客户报价："+quote);
        }
    }
