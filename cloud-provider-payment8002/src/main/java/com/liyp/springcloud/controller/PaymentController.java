package com.liyp.springcloud.controller;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import com.liyp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*********" + service +  "*********");
        }
        log.info("*********instances*********");
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" +instance.getUri());
        }
        return new CommentResult(200, "成功", this.discoveryClient);
    }

    @GetMapping("/payment/serverPort")
    public String getServerPort() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String timeOut() {
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
