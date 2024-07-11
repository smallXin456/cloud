package com.atguigu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

//启动注解
@SpringBootApplication
//mapper代码生成的注解，全局扫描com.atguigu.cloud下的mapper
@MapperScan("com.atguigu.cloud.mapper")
@EnableDiscoveryClient
@RefreshScope  //动态刷新
public class Main8002 {

    public static void main(String[] args) {
        SpringApplication.run(Main8002.class,args);
    }
}