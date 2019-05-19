package com.chris.chriseurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
//HystrixDashboard 引入
@EnableHystrixDashboard
public class ChrisEurekaClientApplication {
    /**
     * @SpringCloudApplication 包含下面注解
     *
     * @SpringBootApplication
     * @EnableDiscoveryClient
     * @EnableCircuitBreaker
     * */
    public static void main(String[] args) {
        SpringApplication.run(ChrisEurekaClientApplication.class, args);

    }

}
