package com.hh.core.basic.lamda;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2020/1/2.
 * 实体类
 */
@Data
public class People {

    private String name;

    private int age;

    public People() {}

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static List<People> initPeopleList() {
        return new ArrayList<People>() {
            {
                add(new People("xiaoming", 10));
                add(new People("xiaohua", 20));
                add(new People("xiaoli", 15));
            }
        };
    }

}
