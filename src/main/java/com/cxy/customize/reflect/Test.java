package com.cxy.customize.reflect;

import java.lang.reflect.Field;

/**
 * Description:   </br>
 * Date: 2020/7/5 17:39
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Test {

    public static void main(String[] args) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, Exception {
        ReflectPointer rp1 = new ReflectPointer(3,4);
        changeBtoA(rp1);
        System.out.println(rp1);

    }

    //1. 将对象的Str域 中的b改成a
    private static void changeBtoA(Object obj) throws RuntimeException, Exception {
        Field[] fields = obj.getClass().getFields();

        for(Field field : fields) {
            //用equals语义不准确
            if(field.getType()==String.class) {
                String oldValue = (String)field.get(obj);
                String newValue = oldValue.replace('b', 'a');
                field.set(obj,newValue);
            }
        }
    }

 static class ReflectPointer {

    private int x = 0;
    public int y = 0;
    public String str1 = "ball";
    public String str2 = "basketball";
    public String str3 = "itcat";

    public ReflectPointer(int x,int y) {//alt + shift +s相当于右键source
        super();
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "ReflectPointer [str1=" + str1 + ", str2=" + str2 + ", str3="
                + str3 + "]";
    }
}

}
