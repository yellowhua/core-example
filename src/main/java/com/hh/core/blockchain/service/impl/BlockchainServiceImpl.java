package com.hh.core.blockchain.service.impl;

import com.hh.core.blockchain.entity.User;
import com.hh.core.blockchain.mapper.BlockchainMapper;
import com.hh.core.blockchain.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类描述
 *
 * @author hh
 * @date 2021/5/28 0028
 */
@Service
@RequiredArgsConstructor
public class BlockchainServiceImpl implements BlockchainService {

    private final BlockchainMapper blockchainMapper;

    @Override
    public void add(User user) {
        blockchainMapper.add(user);
    }

    @Override
    public List<User> query() {
        return blockchainMapper.query();
    }

    @Override
    public User queryById(Integer id) {
        return blockchainMapper.queryById(id);
    }

    @Override
    public void edit(User user) {
        blockchainMapper.edit(user);
    }

}
