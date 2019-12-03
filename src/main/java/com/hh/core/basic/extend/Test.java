package com.hh.core.basic.extend;

/**
 * Created by hh on 2019/10/11.
 */
public class Test {

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.askMe();
        parent.callMe();

        Son son = new Son();
        son.askMe();
        son.callMe();
    }

}
