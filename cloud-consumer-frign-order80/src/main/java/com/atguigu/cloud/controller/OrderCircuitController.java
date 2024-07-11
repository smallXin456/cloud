package com.atguigu.cloud.controller;



import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.resp.Result;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;


    @GetMapping("feign/pay/circuit/{id}")
    //保险丝，name=哪个微服务，fallbackMethod = 兜底方法 (服务熔断)
    @CircuitBreaker(name="cloud-payment-service",fallbackMethod = "circuitFallback")
    public String payCircuit(@PathVariable("id") Integer id) {
        return payFeignApi.payCircuit(id);
    }

    //降级
    public String circuitFallback(Throwable t){
        return "系统繁忙，请稍后再试";
    }



    @GetMapping("feign/pay/bulkhead/{id}")
    //舱壁隔离
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "bulkheadFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> bulkhead(@PathVariable("id") Integer id) {

        System.out.println(Thread.currentThread().getName()+"\t"+"正在进入");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"准备离开");
        return CompletableFuture.supplyAsync(() ->payFeignApi.payCircuit(id)+"\t"+"Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> bulkheadFallback(Throwable t){
        return CompletableFuture.supplyAsync(()->"当前线程池已满");
    }





    @GetMapping("feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public String ratelimit(@PathVariable("id") Integer id) {
        return payFeignApi.myRatelimit(id);
    }



    public String myRatelimitFallback(Throwable t){
        return "你被限流了，禁止访问！";
    }





//    监控案例
    @GetMapping("/feign/micrometer/{id}")
    public String micrometer(@PathVariable("id") Integer id){
        return payFeignApi.micrometer(id);
    }





    @GetMapping("/feign/gateway/info/{id}")
    public Result<String> gateway(@PathVariable("id") Integer id) {
        return payFeignApi.gateway(id);
    }

    @GetMapping("/feign/gateway/get/{id}")
    public Result<String> gateway2(@PathVariable("id") Integer id) {
        return payFeignApi.gateway2(id);
    }


}
