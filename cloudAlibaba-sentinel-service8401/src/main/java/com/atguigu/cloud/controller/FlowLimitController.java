package com.atguigu.cloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloud.service.FolwLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @Resource
    private FolwLimitService folwLimitService;


    @GetMapping("testA")
    public String testA(){
        return "--Test is A";
    }


    @GetMapping("testB")
    public String testB(){
        return "--Test is B";
    }


    /**
     * 链路演示demo，达到阈值对c限流，对d不管
     * @return
     */

    @GetMapping("testC")
    public String testC(){
        folwLimitService.common();
        return "--Test is C";
    }


    @GetMapping("testD")
    public String testD(){
        folwLimitService.common();
        return "--Test is D";
    }




    //超时时间后的请求作为超时处理，放弃
    @GetMapping("testE")
    public String testE(){
        System.out.println(System.currentTimeMillis()+" test E 在排队等待");
        return "--Test is E";
    }


    @GetMapping("testF")
    public String testF(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("测试：新增熔断规则-慢调用比例 ");
        return "--Test is f";
    }


    @GetMapping("testG")
    public String testG(){
        int i = 10 / 0;
        return "--Test is G,新增熔断规则，异常比例";
    }






    @GetMapping("/rataLimit/byUrl")
    public String byUrl(){

        return "按rest地址限流测试";
    }


    @GetMapping("rataLimit/byResource/{id}")
    @SentinelResource(value = "byResourceSentinelResource",blockHandler = "handlerBlockHandler",fallback = "fallbackMethod")
    public String byResource(@PathVariable("id") Integer id){
        if (id==0){
            throw new RuntimeException("id 为 0");
        }

        return "按照资源名称限流测试";
    }



    public String handlerBlockHandler(@PathVariable("id") Integer id,BlockException ex) {
        return "服务不可用，限流了，触发了@SentinelResource";
    }


    public String fallbackMethod(@PathVariable("id") Integer id,Throwable e) {
        return "服务不可用，异常了";
    }





    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "handler_testHotKey",fallback = "fallbackMethod")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){

        return "---------testHotKey";
    }



    public String handler_testHotKey(String p1,String p2,BlockException ex) {
        return "-------handler_testHotKey";
    }




    @GetMapping("/empower")
    public String requestSentinel(){
        return "Sentinel授权规则";
    }










}
