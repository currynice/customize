package com.cxy.customize.lambda.stream.map;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/4/10/010.
 */
public class Award {
    private String name;
    private BigDecimal savings;  //savings = salary*age

    public Award() {
        super();
    }

    public Award(String name, BigDecimal savings) {
        this.name = name;
        this.savings = savings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }

    @Override
    public String toString() {
        return "Award{" +
                "name='" + name + '\'' +
                ", savings=" + savings +
                '}';
    }
}
