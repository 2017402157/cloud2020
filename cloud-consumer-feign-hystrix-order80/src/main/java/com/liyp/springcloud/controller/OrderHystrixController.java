package com.liyp.springcloud.controller;

import com.liyp.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@DefaultProperties(defaultFallback = "gloadFullbreak")
public class OrderHystrixController {
    @Autowired
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentFeignHystrixService.paymentInfo_OK(id);
        log.info("*********result:  " + result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")  //3秒钟以内就是正常的业务逻辑
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentFeignHystrixService.paymentInfo_TimeOut(id);
        log.info("*********result:  " + result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "对方服务器繁忙o(╥﹏╥)o";
    }

    public String gloadFullbreak() {
        return "对方服务器繁忙或者运行出错o(╥﹏╥)o";
    }
}
