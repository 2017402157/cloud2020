package com.liyp.springcloud.controller;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181", "贵州"));
        hashMap.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182", "上海"));
        hashMap.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183", "北京"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommentResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommentResult<Payment> result = new CommentResult(200,"from mysql,serverPort:  " + serverPort, payment);
        return result;
    }



}


