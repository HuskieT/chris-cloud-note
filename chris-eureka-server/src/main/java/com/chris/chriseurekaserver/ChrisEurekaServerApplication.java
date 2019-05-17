package com.chris.chriseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: linfei
 * @Description:   @EnableEurekaServer  启动一个服务注册中心，只需要一个注解@EnableEurekaServer
 * @Param:
 * @return:
 * @Date: 2019/5/17
 */
@SpringBootApplication
@EnableEurekaServer
public class ChrisEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChrisEurekaServerApplication.class, args);
    }

}
