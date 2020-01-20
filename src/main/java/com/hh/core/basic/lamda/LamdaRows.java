package com.hh.core.basic.lamda;

/**
 * Created by hh on 2020/1/2.
 * 多行语句
 */
public class LamdaRows {

    public static void main(String[] args) {
        test((s) -> {
            s = s + s;
            System.out.println(s);
        });
    }

    public static void test(MyInterface myInterface) {
        myInterface.say("hhhhh");
    }

    interface MyInterface {
        void say(String s);
    }

}
