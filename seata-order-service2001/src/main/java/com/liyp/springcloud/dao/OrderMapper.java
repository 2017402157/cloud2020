package com.liyp.springcloud.dao;

import com.liyp.springcloud.entities.Order;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;

@Mapper
public interface OrderMapper {
    int insert(Order order);

    int update(@PathParam("userId") Long userId, @PathParam("status") Integer status);
}
