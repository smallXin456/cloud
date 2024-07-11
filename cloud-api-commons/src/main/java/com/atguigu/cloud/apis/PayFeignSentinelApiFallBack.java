package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.CityDTO;
import com.atguigu.cloud.resp.Result;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignApi{
    @Override
    public Result add(CityDTO cityDTO) {
        return null;
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public String payCircuit(Integer id) {
        return "";
    }

    @Override
    public String bulkhead(Integer id) {
        return "";
    }

    @Override
    public String myRatelimit(Integer id) {
        return "";
    }

    @Override
    public String micrometer(Integer id) {
        return "";
    }

    @Override
    public Result<String> gateway(Integer id) {
        return null;
    }

    @Override
    public Result<String> gateway2(Integer id) {
        return null;
    }

    @Override
    public Result getPayOrder(String orderNo) {
        return Result.fail("500","服务降级了");
    }
}
