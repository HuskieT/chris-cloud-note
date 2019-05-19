package com.chris.chriseurekaclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="eruekaClientTwo")
public interface ChrisFeignClient {
    /**
     * @Author: linfei
     * @Description:
     * @Param:
     * @return:
     * @Date: 2019/5/18
     */
    @GetMapping("/msg")
    String getProductMsg();

    @PostMapping("/serverProductList")
    String getProductList(String id);

    @GetMapping("/getProduct/{id}")
    String getProduct(@PathVariable("id")String id);

    @GetMapping("/getProductTwo")
    String getProductTwo(@RequestParam("id")String id,@RequestParam("name") String name);

    @GetMapping("/hystrixTestServer")
    String hystrixTestServer();


}
