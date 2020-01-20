package com.hh.core.basic.lamda;

/**
 * Created by hh on 2020/1/2.
 * 测试类
 */
public class InsideClass {

    public static void main(String[] args) {
        test(new MyInterface() {
            @Override
            public void say() {
                System.out.println("hahahahaha");
            }
        });
    }

    public static void test(MyInterface myInterface) {
        myInterface.say();
    }

    interface MyInterface {
        void say();
    }

}
