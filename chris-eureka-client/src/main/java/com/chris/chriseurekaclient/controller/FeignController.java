package com.chris.chriseurekaclient.controller;

import com.chris.chriseurekaclient.client.ChrisFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: linfei
 * @date: 2019/05/18
 * @Description:
 */
@RestController
@Slf4j
public class FeignController {

    @Autowired
    private ChrisFeignClient chrisFeignClient;

    /**
     * @Author: linfei
     * @Description:
     * @Param:
     * @return:
     * @Date: 2019/5/18
     */
    @GetMapping("/getProductMsg")
    public String getProductMsg(HttpServletRequest request){
        String response = chrisFeignClient.getProductMsg();
        log.info("返回结果:{}", response);
        return response;
    }
    /** 
     * @Author: linfei 
     * @Description:  feign post请求
     * @Param:
     * @return:  
     * @Date: 2019/5/18 
     */
    @PostMapping("productList")
    public String getProductList(String id){
        String response = chrisFeignClient.getProductList(id);
        log.info("返回结果:{}", response);
        return response;
    }
    /** 
     * @Author: linfei 
     * @Description:  feign get请求 @PathVariable 写法
     * @Param:  
     * @return:  
     * @Date: 2019/5/18 
     */ 
    @GetMapping("product/{id}")
    public String product(@PathVariable("id") String id){
        String response = chrisFeignClient.getProduct(id);
        log.info("返回结果:{}", response);
        return response;
    }
    /** 
     * @Author: linfei 
     * @Description: feign get请求 @RequestParam 写法
     * @Param:  
     * @return:  
     * @Date: 2019/5/18 
     */ 
    @GetMapping("productTwo")
    public String productTwo(@RequestParam("id") String id,@RequestParam("name") String name){
        String response = chrisFeignClient.getProductTwo(id,name);
        log.info("返回结果:{}", response);
        return response;
    }

}
