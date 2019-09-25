package com.cxy.customize.lambda;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/4/9/009.
 * https://www.cnblogs.com/zxf330301/p/6586750.html#4282680
 */
public class Developer {
    private String name;
    private BigDecimal salary;
    private Integer age;

    public Developer(String name, BigDecimal salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
