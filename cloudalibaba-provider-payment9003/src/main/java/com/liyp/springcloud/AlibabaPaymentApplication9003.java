package com.liyp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaPaymentApplication9003 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentApplication9003.class, args);
    }
}
