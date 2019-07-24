package com.cxy.customize.json.Jackson.JsonView;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 员工全部信息 测试@JsonView
 * 利用一个简单的继承，
 * 让name 和age只对Normal显示
 * 全部信息对Manager显示
 */

public class Staff implements Serializable {


    private static final long serialVersionUID = 4059184504213106155L;//todo

    @JsonView(CompanyViews.Normal.class)
    private String name;

    @JsonView(CompanyViews.Normal.class)
    private int age;

    @JsonView(CompanyViews.Manager.class)
    private String[] position;              //  Array

    @JsonView(CompanyViews.Manager.class)
    private List<String> skills;            //  List

    @JsonView(CompanyViews.Manager.class)
    private Map<String, BigDecimal> salary; //  Map

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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Map<String, BigDecimal> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, BigDecimal> salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position=" + Arrays.toString(position) +
                ", skills=" + skills +
                ", salary=" + salary +
                '}';
    }
}
