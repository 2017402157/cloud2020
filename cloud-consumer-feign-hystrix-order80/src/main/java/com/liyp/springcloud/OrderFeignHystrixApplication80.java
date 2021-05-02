package com.liyp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class OrderFeignHystrixApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixApplication80.class, args);
    }
}
