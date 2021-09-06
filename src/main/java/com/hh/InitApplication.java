package com.hh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @author huanghua
 * @date 2020/10/23
 * 预加载
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class InitApplication implements ApplicationRunner {

    private final RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) {
        log.info("init");
    }

}
