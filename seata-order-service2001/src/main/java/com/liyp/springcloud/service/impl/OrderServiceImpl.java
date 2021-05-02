package com.liyp.springcloud.service.impl;

import com.liyp.springcloud.dao.OrderMapper;
import com.liyp.springcloud.entities.Order;
import com.liyp.springcloud.service.AccountService;
import com.liyp.springcloud.service.OrderService;
import com.liyp.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "order_create", rollbackFor = Exception.class)
    public int create(Order order) {
        log.info("=================>开始创建订单<=================");
        // 1、创建订单
        orderMapper.insert(order);

        log.info("=================>扣减账户余额<=================");
        // 2、扣减账户余额
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("=================>扣减账户余额结束<=================");

        log.info("=================>扣减库存开始<=================");
        // 3、扣减账户余额
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("=================>扣减库存结束<=================");

        log.info("=================>修改订单状态开始<=================");
        // 4、修改订单状态
        orderMapper.update(order.getUserId(), 0);
        log.info("=================>修改订单状态结束<=================");
        return 0;
    }

    @Override
    public int edit(Long userId, Integer status) {
        return 0;
    }
}
