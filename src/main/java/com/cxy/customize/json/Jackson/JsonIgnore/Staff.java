package com.cxy.customize.json.Jackson.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 员工全部信息 测试@JsonIgnore<br>
 *  默认情况下，Jackson 包括了所有fields(包括static transient修饰的)<br>
 * JsonIgnore注解 在<strong>Field级别</strong> 对field进行过滤<br>
 * JsonIgnoreProperties在 <strong>class级别</strong> 对field进行过滤<br>
 */
@JsonIgnoreProperties({"position","salary"})
public class Staff implements Serializable {


    private static final long serialVersionUID = -4337633343423993536L;
    private String name;
    private int age;
    private String[] position;


    private List<String> skills;


    private Map<String, BigDecimal> salary;

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
