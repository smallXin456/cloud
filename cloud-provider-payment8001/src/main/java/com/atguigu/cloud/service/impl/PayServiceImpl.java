package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.City;
import com.atguigu.cloud.mapper.CityMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private CityMapper cityMapper;

    @Override
    public int add(City city) {
        int i = cityMapper.insertSelective(city);
        return i;
    }
}
