package com.hh.core.basic.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hh on 2020/3/17.
 */
@Data
public class User implements Serializable {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
