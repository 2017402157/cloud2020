package com.liyp.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    //成功
    String paymentInfo_OK(Integer id);

    //失败
    String paymentInfo_TimeOut(Integer id);

    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
