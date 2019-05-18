package com.chris.chriseurekaclienttwo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author: linfei
 * @date: 2019/05/17
 * @Description:  测试ResultTemplate通信方式 （假设服务端）
 */
@RestController
@Slf4j
public class TestTwoController {

    @GetMapping("/msg")
    public String setMsg() {
        String jsonStr = "{\n" +
                "    \"name\": \"BeJson\",\n" +
                "    \"url\": \"http://www.bejson.com\",\n" +
                "    \"page\": 88,\n" +
                "    \"isNonProfit\": true,\n" +
                "    \"address\": {\n" +
                "        \"street\": \"科技园路.\",\n" +
                "        \"city\": \"江苏苏州\",\n" +
                "        \"country\": \"中国\"\n" +
                "    },\n" +
                "    \"links\": [\n" +
                "        {\n" +
                "            \"name\": \"Google\",\n" +
                "            \"url\": \"http://www.google.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Baidu\",\n" +
                "            \"url\": \"http://www.baidu.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"SoSo\",\n" +
                "            \"url\": \"http://www.SoSo.com\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return jsonStr;
    }

    @PostMapping("/serverProductList")
    public String serverProductList( String id) {
        log.info("服务端接受数据id："+id);
        return "ProductList  1,,2,3,4";
    }
    @GetMapping("/getProduct/{id}")
    public String getProduct(@PathVariable("id") String id) {
        log.info("服务端接受数据id："+id);
        return "this is product";
    }
    @GetMapping("/getProductTwo")
    public String getProductTwo(@RequestParam("id") String id,@RequestParam("name") String name) {
        log.info("服务端接受数据id："+id);
        return "this is product";
    }


}
