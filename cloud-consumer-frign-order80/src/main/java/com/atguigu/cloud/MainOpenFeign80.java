package com.atguigu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  //服务注册、发现和通信
@EnableFeignClients    //启用feign客户端,定义服务+绑定接口
public class MainOpenFeign80 {

    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign80.class,args);
    }

}