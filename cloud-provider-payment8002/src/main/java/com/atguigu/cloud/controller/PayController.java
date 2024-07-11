package com.atguigu.cloud.controller;


import com.atguigu.cloud.entities.City;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name ="支付模块",description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "json串做参数")
    public Result<Integer> add(@RequestBody City city){
        System.out.println(city.getName());
        System.out.println(city.getCountrycode());
        int add = payService.add(city);
        if(add>0){
            return Result.success(add,port);
        }
       return  Result.fail();
    }



    @GetMapping(value = "/pay/get/info")
    public String getInfo(@Value("${atguigu.info}")String info){
        return "atguiguInfo:"+info+",port:"+port;
    }

}
