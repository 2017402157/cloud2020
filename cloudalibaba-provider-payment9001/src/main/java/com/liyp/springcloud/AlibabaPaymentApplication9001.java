package com.liyp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentApplication9001.class, args);
    }
}
