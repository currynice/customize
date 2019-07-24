package com.cxy.customize.json.Jackson.parse;

import java.io.Serializable;
import java.util.Arrays;


/**
 * 员工基本信息
 */
public class SimpleStaff implements Serializable {


    private static final long serialVersionUID = -3647515214055600310L;
    private String name;
    private int age;
    private String[] position;              //  Array

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

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    @Override
    public String
    toString() {
        return "SimpleStaff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position=" + Arrays.toString(position) +
                '}';
    }
}
