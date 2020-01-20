package com.hh.core.basic.lamda;

/**
 * Created by hh on 2020/1/2.
 * 单行语句有参数
 */
public class LamdaRowWithParam {

    public static void main(String[] args) {
        test((s) -> System.out.println(s));
    }


    public static void test(MyInterface myInterface) {
        myInterface.say("hhhhh");
    }

    interface MyInterface {
        void say(String s);
    }

}
