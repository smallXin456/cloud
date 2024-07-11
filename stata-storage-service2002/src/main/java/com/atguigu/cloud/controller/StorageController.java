package com.atguigu.cloud.controller;


import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;



    @PostMapping("/storage/decrease")
    public Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {

        System.out.println("我进来了");
        System.out.println(productId+"  "+count);
        storageService.decrease(productId,count);


        return Result.success("扣减成功","2002");
    }
}
