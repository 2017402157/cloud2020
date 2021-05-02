package com.liyp.springcloud.controller;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import com.liyp.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public final static String PAYMENT_URL = "http://localhost:8001";
    public final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/add")
    public CommentResult<Payment> add(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommentResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommentResult<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/"+id, CommentResult.class);
    }


    @GetMapping("/consumer/payment/discovery")
    public CommentResult<Payment> getDiscovery() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/discovery", CommentResult.class);
    }
    @GetMapping("/consumer/getEntity/{id}")
    public CommentResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, CommentResult.class);
        log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommentResult<>(500, "操作失败");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/serverPort", String.class);
    }
}
