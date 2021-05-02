package com.liyp.springcloud.controller;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import com.liyp.springcloud.service.PaymentOpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    @GetMapping("/consumer/payment/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id") Long id) {
        return paymentOpenFeignService.select(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String getTimeout() {
        return paymentOpenFeignService.timeOut();
    }
}
