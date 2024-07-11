package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Storage;
import com.atguigu.cloud.resp.Result;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;


public interface StorageMapper extends Mapper<Storage> {


    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}