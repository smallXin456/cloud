package com.atguigu.cloud.apis;


import com.atguigu.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {


    @PostMapping("/account/decrease")
    Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
