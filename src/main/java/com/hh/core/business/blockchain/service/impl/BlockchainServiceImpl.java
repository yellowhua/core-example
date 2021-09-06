package com.hh.core.business.blockchain.service.impl;

import com.alipay.mychain.sdk.message.transaction.account.DepositDataResponse;
import com.hh.core.business.blockchain.entity.User;
import com.hh.core.business.blockchain.mapper.BlockchainMapper;
import com.hh.core.business.blockchain.service.BlockchainService;
import com.hh.core.business.blockchain.util.BlockchainUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

    private final BlockchainMapper blockchainMapper;

    @Override
    public void add(User user) {
        blockchainMapper.add(user);

        // 数据上链
        try {
            BlockchainUtil.initMychainEnv();
            BlockchainUtil.initSdk();
            DepositDataResponse response = BlockchainUtil.depositdata(user);
            log.info("{}", response.getTxHash());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public List<User> query() {
        return blockchainMapper.query();
    }

    @Override
    public User queryById(String id) {
        return blockchainMapper.queryById(id);
    }

    @Override
    public void edit(User user) {
        blockchainMapper.edit(user);
    }

}
