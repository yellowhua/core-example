package com.hh.core.basic.lamda;

/**
 * Created by hh on 2020/1/2.
 * 单行语句无参数
 */
public class LamdaRowWithoutParam {

    public static void main(String[] args) {
        test(() -> System.out.println("hahahahahaha"));
    }

    public static void test(MyInterface myInterface) {
        myInterface.say();
    }

    interface MyInterface {
        void say();
    }

}
