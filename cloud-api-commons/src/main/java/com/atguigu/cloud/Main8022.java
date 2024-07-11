package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.ZonedDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class  Main8022{

    public static void main(String[] args) {
        SpringApplication.run(Main8022.class,args);


        //默认时区
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);

    }

}