package com.liyp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication3366.class, args);
    }
}
