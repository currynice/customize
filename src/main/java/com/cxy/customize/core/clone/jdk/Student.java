package com.cxy.customize.core.clone.jdk;

//浅克隆，需要实现java.lang.Cloneable接口，这是一个空接口，要实现克隆功能，需要重写Object.clone方法
public class Student implements Cloneable {
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //返回类型是Object,需要强制转型
    @Override
    public Object clone()throws CloneNotSupportedException{
        Object object = super.clone();
        return object;
    }






}
