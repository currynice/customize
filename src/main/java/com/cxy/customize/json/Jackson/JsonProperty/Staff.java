package com.cxy.customize.json.Jackson.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * 员工全部信息 测试@JsonProperty
 */

public class Staff implements Serializable {
    private static final long serialVersionUID = -6887495293008068361L;



    @JsonProperty(value = "name2")//改变名字
    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
