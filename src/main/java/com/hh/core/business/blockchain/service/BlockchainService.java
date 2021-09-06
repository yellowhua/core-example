package com.hh.core.business.blockchain.service;

import com.hh.core.business.blockchain.entity.User;

import java.util.List;

/**
 * 类描述
 *
 * @author hh
 * @date 2021/5/28 0028
 */
public interface BlockchainService {

    void add(User user);

    List<User> query();

    User queryById(String id);

    void edit(User user);
}
