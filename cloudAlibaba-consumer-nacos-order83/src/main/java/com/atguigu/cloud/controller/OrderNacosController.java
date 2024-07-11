package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.PrivateKey;

@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;


    @Resource
    private PayFeignApi payFeignApi;


    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        String restult = restTemplate.getForObject(serverURL+"/pay/nacos/"+id,String.class);
        return restult+"\t"+"我是orderNacos83的调用者";
    }



    @GetMapping("/consumer/pay/nacos/get/{orderNo}")
    public Result getPayOrder(@PathVariable("orderNo") String orderNo){
        return  payFeignApi.getPayOrder(orderNo);
    }


}
