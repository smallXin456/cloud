package com.atguigu.cloud.service;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class FolwLimitService {

    //哨兵需要的资源
    @SentinelResource(value = "common")
    public void common(){
        System.out.println("---------FolwLimitService come in");
    }
}
