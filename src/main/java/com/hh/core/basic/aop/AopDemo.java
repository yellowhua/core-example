package com.hh.core.basic.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huanghua
 * @date 2020/12/2
 */
@Slf4j
@Component
public class AopDemo {

    public List<String> query(List<String> list) {
        for (String str : list) {
            log.info(str);
        }
        return list;
    }

}
