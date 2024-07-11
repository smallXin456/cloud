package com.atguigu.cloud.controller;





import com.atguigu.cloud.entities.CityDTO;
import com.atguigu.cloud.resp.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@Tag(name ="支付模块",description = "支付CRUD")
public class PayController {

    public static String Url = "http://cloud-payment-service";

    @Resource
    //是spring提供访问rest服务的客户模板工具
    private RestTemplate restTemplate;





    @GetMapping(value = "/customer/pay/add")
    public Result addOrder(CityDTO cityDTO){

        return restTemplate.postForObject(Url+"/pay/add",cityDTO,Result.class);
    }





    @GetMapping(value = "/customer/pay/info")
    public String getInfo( ){
        return restTemplate.getForObject(Url+"/pay/get/info",String.class);
    }




}
