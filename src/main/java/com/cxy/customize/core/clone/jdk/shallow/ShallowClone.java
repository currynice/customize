package com.cxy.customize.core.clone.jdk.shallow;

public class ShallowClone {
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setAge(24);
        student.setName("cxy");
        Student student2 = (Student)student.clone();

        System.out.println("Age:" + student2.getAge() + " " + "Name:" + student2.getName());

        System.out.println("---------------------");
        student2.setAge(23);
        //克隆后得到的是一个新的对象，所以重新写的是student2这个对象的值

        System.out.println(student.getAge());
        System.out.println(student2.getAge());
    }

}
