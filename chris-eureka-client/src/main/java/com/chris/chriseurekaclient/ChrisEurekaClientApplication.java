package com.chris.chriseurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChrisEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChrisEurekaClientApplication.class, args);
    }

}
