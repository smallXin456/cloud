package com.atguigu.cloud.controller;


import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.City;
import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

@RestController
public class PayGateWayController {

    @Resource
    PayService payService;

    @Value("${server.port}")
    private String port;

//    @PostMapping(value = "/pay/gateway/add")
//    @Operation(summary = "新增",description = "json串做参数")
//    public Result<Integer> add(@RequestBody City city){
//
//        //暂停线程62秒
////        try {
////            TimeUnit.SECONDS.sleep(62);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
//
//
//        int add = payService.add(city);
//
//        return  Result.success(add,port);
//    }



    @GetMapping("/pay/gateway/info/{id}")
    public Result<String> gateway(@PathVariable("id") Integer id) {
        return Result.success("欢迎来到gateway"+id+"\t" + IdUtil.simpleUUID(),port);
    }

    @GetMapping("/pay/gateway/get/{id}")
    public Result<String> gateway2(@PathVariable("id") Integer id) {
        return Result.success("欢迎来到gateway get "+id+"\t" + IdUtil.simpleUUID(),port);
    }



    @GetMapping("pay/gateway/filter")
    public Result<String> gatewayFilter(HttpServletRequest request) {

        System.out.println("---");

        System.out.println(request.getParameter("customerId"));
        System.out.println(request.getParameter("customerName"));


        String result =  "";
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("请求名:"+headerName+"\t"+"请求值:"+headerValue);

            if (headerName.equalsIgnoreCase("X-Request-atguigu")
        || headerName.equalsIgnoreCase("X-Request-atguigu2")){
                result = result+headerName+"\t"+headerValue;
            }
        }
        return Result.success("gatewayFilter:"+result,port);
    }

}
