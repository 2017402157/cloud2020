package com.liyp.springcloud.controller;

import com.liyp.springcloud.entities.CommResult;
import com.liyp.springcloud.entities.Order;
import com.liyp.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommResult createOrder(Order order) {
        return new CommResult(200, "订单创建成功", orderService.create(order));
    }
}
