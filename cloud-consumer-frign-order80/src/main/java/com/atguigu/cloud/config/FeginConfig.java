package com.atguigu.cloud.config;


import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    @Bean
    public Retryer myRetryer(){


        return Retryer.NEVER_RETRY;  //不走重试机制

        //重启三次
//        return new Retryer.Default(500, 1, 3);
    }


    /**
     * NONE   默认的，不开启
     * BASIC   仅记录请求方法、URL、响应状态码、执行时间
     * HEADERS  除了BASIC还有请求、响应的头信息
     * FULL     除了HEADERS，还有响应的正文及元数据
     * @return
     */


    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


}
