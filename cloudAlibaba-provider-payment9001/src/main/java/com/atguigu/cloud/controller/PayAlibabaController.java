package com.atguigu.cloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloud.resp.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id){
        return "nacos serverPort is:"+serverPort+"\t"+id;
    }





    //openFeign+sentinel进行服务降级和流量监控的整合处理

    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayOrder",blockHandler = "handlerBlockHandler")
    public Result getPayOrder(@PathVariable("orderNo")String orderNo){
         int i = 10/0;
        return Result.success(null,serverPort);
    }

    public Result handlerBlockHandler(@PathVariable("orderNo")String orderNo, BlockException ex){
        return Result.fail("500","getPayOrder服务不可用，触发了sentinel的配置规则");
    }





}
