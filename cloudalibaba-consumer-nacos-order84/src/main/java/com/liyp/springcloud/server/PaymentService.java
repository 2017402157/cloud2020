package com.liyp.springcloud.server;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    CommentResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
