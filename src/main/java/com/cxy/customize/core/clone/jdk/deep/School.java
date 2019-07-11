package com.cxy.customize.core.clone.jdk.deep;

import java.io.*;

public class School implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private Teacher teacher;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * 序列化方式实现深拷贝
     * 将当前这个对象写到一个输出流当中，只要实现了序列化，都会写到这个输出流当中
     * @return
     */
    public Object deepCopt()throws Exception{

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try{
            ByteArrayOutputStream  bos = new ByteArrayOutputStream();
             oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
             ois = new ObjectInputStream(bis);
            return ois.readObject();


        }catch (IOException|ClassNotFoundException e){
            throw new Exception("深复制失败"+e);
        }finally {
            oos.close();
            ois.close();
        }
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
