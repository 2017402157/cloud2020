package com.liyp.springcloud.contoller;

import com.liyp.springcloud.entities.CommResult;
import com.liyp.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;


    //扣减库存
    @PostMapping("/storage/decrease")
    public CommResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommResult(200,"扣减库存成功！");
    }

    @GetMapping("/storage/test")
    public String test(){
        return "test";
    }

}
