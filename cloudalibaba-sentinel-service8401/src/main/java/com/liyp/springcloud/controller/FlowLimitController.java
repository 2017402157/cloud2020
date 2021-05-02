package com.liyp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/testA")
    public String testA() {
        return "---------testA--------- \t port: " + serverPort;
    }

    @GetMapping("/testB")
    public String testB() {

        return "------testB--------- \t port: " + serverPort;
    }

    @GetMapping("/testD")
    public String testD()
    {
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        log.info("testD 测试RT");

        int i = 10/0;
        log.info("testD 测试 \t 异常比例");

        return "------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey", fallback = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }

    //兜底方法
    public String deal_testHotKey (String p1, String p2, BlockException exception){
        return "------deal_testHotKey,o(╥﹏╥)o";
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommentResult byUrl()
    {
        return new CommentResult(200,"按url限流测试OK",new Payment(2020L,"serial002", "贵州"));
    }

}
