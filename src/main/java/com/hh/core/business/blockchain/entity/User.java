package com.hh.core.business.blockchain.entity;

import lombok.Data;

/**
 * 类描述
 *
 * @author hh
 * @date 2021/5/28 0028
 */
@Data
public class User {

    private String id;

    private String username;

    private Integer age;

    public User() { }

    public User(String id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
