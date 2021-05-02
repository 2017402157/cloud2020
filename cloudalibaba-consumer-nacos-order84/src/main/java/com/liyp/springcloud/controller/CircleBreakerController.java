package com.liyp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import com.liyp.springcloud.server.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommentResult<Payment> fallback(@PathVariable Long id) {
        CommentResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id, CommentResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    //fallback
    public CommentResult handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null", "null");
        return new CommentResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    //blockHandler
    public CommentResult blockHandler(@PathVariable  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null", "null");
        return new CommentResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }


    //=====openfeign
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{ID}")
    public CommentResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}
