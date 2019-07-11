package com.cxy.customize.core.clone.jdk.deep;

public class DeepClone {
    public static void main(String[] args) throws Exception
    {

//        Student student = new Student();
//        student.setAge(22);
//        student.setName("cxy3");
//        Group group = new Group();
//        group.setId(1);
//        group.setStudent(student);
//        Group group2 =(Group)group.clone();
//        student.setAge(30);
//        System.out.println(group2.toString());

        Teacher teacher = new Teacher();
        teacher.setAge(22);
        teacher.setName("cxy3");
        School school = new School();
        school.setName("xx大学");
        school.setTeacher(teacher);
        School school2 =(School)school.deepCopt();
        teacher.setAge(30);
        System.out.println(school2.toString());


    }

}

