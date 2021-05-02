package com.liyp.springcloud.service;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentOpenFeignService {

    @GetMapping("/payment/{id}")
    CommentResult<Payment> select(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    String timeOut();
}
