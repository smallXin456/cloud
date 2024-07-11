package com.atguigu.cloud.exp;


import com.atguigu.cloud.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//拦截器
@RestControllerAdvice
public class GlobaExp {

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e){

        System.out.println("##GlobaExp");
        log.error("全局信心异常:{}",e.getMessage(),e);
        return Result.fail();
    }

}
