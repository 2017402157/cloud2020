package com.liyp.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFeignHystrixFallbackService implements PaymentFeignHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----------PaymentFeignHystrixService of paymentInfo_OK-------";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----------PaymentFeignHystrixService of paymentInfo_TimeOut-------";
    }
}
