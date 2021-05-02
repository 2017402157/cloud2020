package com.liyp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(value = {"com.liyp.springcloud.dao"})
public class SeataAccountApplication2003 {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountApplication2003.class, args);
    }
}
