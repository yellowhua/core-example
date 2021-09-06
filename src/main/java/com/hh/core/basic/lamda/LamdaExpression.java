package com.hh.core.basic.lamda;

/**
 * Created by hh on 2020/1/2.
 * 表达式
 */
public class LamdaExpression {

    public static void main(String[] args) {
        test((x, y) -> System.out.println(x + y));
    }

    public static void test(MyInterface myInterface) {
        myInterface.say("hhhhh", "aaaaa");
    }

    interface MyInterface {
        void say(String x, String y);
    }

}
