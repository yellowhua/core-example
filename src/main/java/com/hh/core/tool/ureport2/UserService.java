package com.hh.core.tool.ureport2;

import com.hh.core.basic.serializable.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：SpringBean数据源配置
 * </pre>
 *
 * @类名： com.hh.core.tool.ureport2.UserService
 * @作者： huanghua
 * @创建日期: 2022/1/17 17:16
 */
@Service("userService")
public class UserService {
    /**
     * 测试数据
     * @return List<User>
     */
    public List<User> queryUser(String dsName, String datasetName, Map<String, Object> parameters) {
        List<User> list = new ArrayList<>();
        User user1 = new User("name1", 1);
        User user2 = new User("name2", 2);
        list.add(user1);
        list.add(user2);
        return list;
    }
}
