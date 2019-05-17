package com.chris.chriseurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: linfei
 * @date: 2019/05/17
 * @Description: 测试ResultTemplate通信方式 （假设客户端）
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * ResultTemplate第一种调用方式
     * 缺点:url是写死的 多个访问地址对于这个方法是一个大考验
     *
     * @return
     */
    @GetMapping("/getProductMsg1")
    public String getProductMsg1() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8003/msg", String.class);
        log.info("返回结果:{}", result);
        return result;
    }

    /**
     * 第二种方式
     * 使用 LoadBalancerClient 通过应用名获取url 然后在使用RestTemplate
     *
     * @return
     */
    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eruekaClientTwo");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        log.info("返回结果:{}", result);
        return result;
    }

    /**
     * 第三种方式
     * 使用注解  需要config下的RestTemplateConfig配置类
     */
    @GetMapping("/getProductMsg3")
    public String getProductMsg3() {
        return restTemplate.getForObject("http://ERUEKACLIENTTWO/msg", String.class);
    }
}
