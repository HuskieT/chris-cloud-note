package com.chris.chriseurekaclienttwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChrisEurekaClienttwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChrisEurekaClienttwoApplication.class, args);
    }

}
