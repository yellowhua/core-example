package com.hh.core.basic.extend;

import lombok.Data;

/**
 * Created by hh on 2019/10/11.
 * 父类
 */
@Data
public class Parent {

    private Integer id;

    private String name;

    public void askMe() {
        System.out.println("parent ask me");
    }

    public void callMe() {
        System.out.println("parent call me");
    }
}
