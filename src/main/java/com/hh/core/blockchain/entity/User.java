package com.hh.core.blockchain.entity;

import lombok.Data;

/**
 * 类描述
 *
 * @author hh
 * @date 2021/5/28 0028
 */
@Data
public class User {

    private Integer id;

    private String username;

    private Integer age;

    public User() { }

    public User(Integer id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
