package com.chris.chriseurekaservertwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ChrisEurekaServertwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChrisEurekaServertwoApplication.class, args);
    }

}
