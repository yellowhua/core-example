package com.hh.core.basic.extend;

/**
 * Created by hh on 2019/10/11.
 * 父类
 */
public class Parent {

    private Integer id;

    private String name;

    public void askMe() {
        System.out.println("parent ask me");
    }

    public void callMe() {
        System.out.println("parent call me");
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
