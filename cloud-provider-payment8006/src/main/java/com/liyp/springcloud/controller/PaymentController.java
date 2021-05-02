package com.liyp.springcloud.controller;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import com.liyp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentzk() {
        return "spring cloud with consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }

    @PostMapping("/payment/add")
    public CommentResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        if (i != 0) {
            return new CommentResult(200, "插入成功" + serverPort, i);
        } else {
            return new CommentResult(500, "插入失败" + serverPort);
        }
    }

    @GetMapping("/payment/{id}")
    public CommentResult select(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        if (result != null) {
            return new CommentResult(200, "查询成功, serverPort: " + serverPort, result);
        } else {
            return new CommentResult(500, "查询失败，查询ID："+ id);
        }
    }
}
