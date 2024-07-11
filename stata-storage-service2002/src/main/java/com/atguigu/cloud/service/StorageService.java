package com.atguigu.cloud.service;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface StorageService {

    void decrease(Long productId, Integer count);


}
