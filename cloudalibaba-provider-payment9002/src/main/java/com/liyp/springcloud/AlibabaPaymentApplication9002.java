package com.liyp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentApplication9002.class, args);
    }
}
