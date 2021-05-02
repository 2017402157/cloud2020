package com.liyp.springcloud.service;

import com.liyp.springcloud.entities.CommResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    CommResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
