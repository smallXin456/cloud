package com.atguigu.cloud.controller;


import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {


    @GetMapping("/pay/circuit/{id}")
    public String payCircuit(@PathVariable("id") Integer id) {

        if (id==9999){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (id == -4) {
            throw new RuntimeException("id 不能为负数");
        }
        return id + "\t" + IdUtil.simpleUUID();
    }









    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id==9999){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (id == -4) {
            throw new RuntimeException("Bulkhead id 不能为负数");
        }
        return "hello，myBulkhead" +id + "\t" + IdUtil.simpleUUID();
    }



    @GetMapping("/pay/ratelimit/{id}")
   public String myRatelimit(@PathVariable("id") Integer id) {
        return "欢迎来到"+id+"\t" + IdUtil.simpleUUID();
    }





    @GetMapping("/pay/micrometer/{id}")
    public String micrometer(@PathVariable("id") Integer id) {
        return "欢迎来到micrometer"+id+"\t" + IdUtil.simpleUUID();
    }






}
