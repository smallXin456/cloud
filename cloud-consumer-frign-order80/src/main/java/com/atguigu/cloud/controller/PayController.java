package com.atguigu.cloud.controller;





import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.CityDTO;
import com.atguigu.cloud.resp.Result;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@Tag(name ="支付模块",description = "支付CRUD")
public class PayController {

    @Resource
    private PayFeignApi payFeignApi;


    @PostMapping("/feign/pay/add")
    public Result add (@RequestBody CityDTO cityDTO) {
        Result result = null;

        try {
            System.out.println("调用开始----"+ DateUtil.now());
            result = payFeignApi.add(cityDTO);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用结束----"+ DateUtil.now());
        return Result.fail();
        }

       return result;
    }



    @GetMapping("/feign/get/info")
    public String getInfo () {
        String result = payFeignApi.getInfo();
        return result;
    }































}
