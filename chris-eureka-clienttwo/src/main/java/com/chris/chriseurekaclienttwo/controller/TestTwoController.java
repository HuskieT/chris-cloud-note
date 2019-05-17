package com.chris.chriseurekaclienttwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: linfei
 * @date: 2019/05/17
 * @Description:  测试ResultTemplate通信方式 （假设服务端）
 */
@RestController
public class TestTwoController {

    @GetMapping("/msg")
    public String setMsg() {
        return "this is product msg";
    }
}
