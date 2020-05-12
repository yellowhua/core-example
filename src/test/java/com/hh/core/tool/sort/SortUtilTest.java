package com.hh.core.tool.sort;

import com.hh.core.tool.sort.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hh on 2018/8/1.
 * 排序类测试
 */
public class SortUtilTest {

    @Test
    public void testSortArr() {

    }

    @Test
    public void testSortObjectListOne() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("新大陆");
        users.add(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setName("亚信");
        users.add(user2);
        User user3 = new User();
        user3.setId(3);
        user3.setName("华为");
        users.add(user3);
        SortUtil.sortObjectListOne(users);
    }

    @Test
    public void testSortObjectListTwo() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("新大陆");
        users.add(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setName("亚信");
        users.add(user2);
        User user3 = new User();
        user3.setId(3);
        user3.setName("华为");
        users.add(user3);
        SortUtil.sortObjectListTwo(users);
    }

    @Test
    public void testSortMapList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", "新大陆");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("b", "华为");
        list.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("c", "亚信");
        list.add(map3);
        SortUtil.sortMapList(list);
    }

}
