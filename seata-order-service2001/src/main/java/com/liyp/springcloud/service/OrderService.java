package com.liyp.springcloud.service;

import com.liyp.springcloud.entities.Order;

public interface OrderService {
    int create(Order order);

    int edit(Long userId, Integer status);
}
