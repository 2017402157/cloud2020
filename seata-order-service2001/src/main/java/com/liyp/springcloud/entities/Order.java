package com.liyp.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 账户余额
     */
    private BigDecimal money;

    /**
     * 订单状态
     */
    private Integer status; //订单状态：0：创建中；1：已完结



}
