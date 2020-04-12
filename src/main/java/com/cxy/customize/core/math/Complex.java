package com.cxy.customize.core.math;

/**
 * @Author: cxy
 * @Date: 2020/4/12 16:05
 * @Description:  复数 todo 工业强度的不可变类
 *
 * 加法法则：（a+bi）+（c+di）=（a+c）+（b+d）i；
 * 减法法则：（a+bi）-（c+di）=（a-c）+（b-d）i；
 * 乘法法则：（a+bi）·（c+di）=（ac-bd）+（bc+ad）i；
 * 除法法则：（a+bi）/（c+di）=[（ac+bd）/（c²+d²）]+[（bc-ad）/（c²+d²）]i.
 */
    public  class Complex {
        public static final  Complex ZERO = new Complex(0,0);
    public static final  Complex ONE = new Complex(1,0);
    public static final  Complex I = new Complex(0,1);

        //实部
        private final double re;

        //虚部
        private final double im;

        //不可变类中用final修饰的所有域必须在创建该实例的时候提供 todo
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }


    //函数functional方法,对操作数运算但不修改，因此使用介词plus作为函数名, 相对的是过程方法（命令方法）
    //BigDecimal的方法也是函数方法，并不修改操作数，而且函数名add是动词，有歧义，导致了不了解API细节的同学入坑（。。。）
    public Complex plus(Complex c){
        return new Complex(re+c.re,im+c.im);
    }


    public Complex minus(Complex c){
        return new Complex(re-c.re,im-c.im);
    }


    public Complex mult(Complex c){
        return new Complex(re*c.re-im*c.im,re*c.im+im*c.re);
    }

    public Complex divide(Complex c){
        double temp = c.re*c.re+c.im*c.im;
        return new Complex((re*c.re+im*c.im)/temp,(im*c.re-re*c.im)/temp);
    }


    @Override
    public boolean equals(Object obj) {
        if(obj==this){
           return true;
        }

        if(!(obj instanceof Complex)){
           return false;
        }

        Complex c = (Complex) obj;
        //使用算术运算符繁琐且容易出错
        return Double.compare(c.re,re)==0&& Double.compare(c.im,im)==0;
    }

    @Override
    public int hashCode() {
        return 31*Double.hashCode(re)+Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "Complex{" +
                re +
                "+" + im +
                'i';
    }
}
