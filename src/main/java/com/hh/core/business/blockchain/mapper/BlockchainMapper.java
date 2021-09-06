package com.hh.core.business.blockchain.mapper;

import com.hh.core.business.blockchain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类描述
 *
 * @author hh
 * @date 2021/5/28 0028
 */
@Mapper
public interface BlockchainMapper {

    void add(@Param("user") User user);

    List<User> query();

    User queryById(String id);

    void edit(@Param("user") User user);
}
