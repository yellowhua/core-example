package com.hh.core.basic.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghua
 * @date 2020/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AopDemoTest {

    @Autowired
    private AopDemo aopDemo;

    @Test
    public void testQuery() {
        List<String> a = null;
        List<String> x = aopDemo.query(a);
        log.info("{}", x);

        List<String> b = new ArrayList<>();
        List<String> y = aopDemo.query(b);
        log.info("{}", y);

        List<String> c = new ArrayList<>();
        c.add("1");
        c.add("2");
        List<String> z = aopDemo.query(c);
        log.info("{}", z);
    }

}
