package com.atguigu.cloud.apis;


import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.CityDTO;
import com.atguigu.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-payment-service")
//@FeignClient(value = "cloud-gateway")
@FeignClient(value = "nacos-payment-provider",fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignApi {


    @PostMapping("/pay/add")
    public Result add(@RequestBody CityDTO  cityDTO);



    @GetMapping("/pay/get/info")
    public String getInfo();



    @GetMapping(value = "/pay/circuit/{id}")
    public String payCircuit(@PathVariable("id") Integer id);


    @GetMapping(value = "/pay/bulkhead/{id}")
    public String bulkhead(@PathVariable("id") Integer id);


    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping("/pay/micrometer/{id}")
    public String micrometer(@PathVariable("id") Integer id);


    @GetMapping("/pay/gateway/info/{id}")
    public Result<String> gateway(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/get/{id}")
    public Result<String> gateway2(@PathVariable("id") Integer id);



    @GetMapping("/pay/nacos/get/{orderNo}")
    public Result getPayOrder(@PathVariable("orderNo")String orderNo);




}
