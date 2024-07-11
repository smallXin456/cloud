package com.atguigu.cloud.apis;


import com.atguigu.cloud.resp.Result;
import org.apache.tomcat.util.digester.RuleSet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {


    @PostMapping("/storage/decrease")
    Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
