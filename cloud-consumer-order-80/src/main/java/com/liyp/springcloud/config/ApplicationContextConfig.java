package com.liyp.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    // @LoadBalanced // 赋予RestTemplate均衡负载功能
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
