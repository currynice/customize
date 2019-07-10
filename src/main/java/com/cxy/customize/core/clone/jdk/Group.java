package com.cxy.customize.core.clone.jdk;

//深克隆
public class Group implements Cloneable{

    private int id;

    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", student=" + student +
                '}';
    }

    @Override
    public Object clone()throws CloneNotSupportedException {
        //这一步返回的这个group还只是一个浅克隆，
        Group group = (Group) super.clone();
        //然后调用这个getStudent这个方方法得到这个Student对象。然后实现克隆。在设置到这个group中的Student。
        //这样实现了双层克隆使得那个student对象也得到了复制。
        group.setStudent((Student) group.getStudent().clone());
        //双层克隆使得那个student对象也得到了复制
        return group;
    }

}
