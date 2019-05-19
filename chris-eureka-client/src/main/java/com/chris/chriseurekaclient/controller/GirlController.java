package com.chris.chriseurekaclient.controller;

import com.chris.chriseurekaclient.config.GirlTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 廖师兄
 * 2018-01-21 21:57
 */
@RestController
@Slf4j
public class GirlController {

    @Autowired
    private GirlTestConfig girlTestConfig;
    @GetMapping("/girl/print")
    public String print() {
        log.info(girlTestConfig.toString());
        return girlTestConfig.getName();
    }
}
