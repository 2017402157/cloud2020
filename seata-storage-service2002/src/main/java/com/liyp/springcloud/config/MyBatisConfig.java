package com.liyp.springcloud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.liyp.springcloud.dao"})
public class MyBatisConfig {
}
