package com.atguigu.cloud.resp;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Result<T> {

    private String code;
    private String message;
    private T data;
    private long time;


    public Result() {
        this.time = System.currentTimeMillis() ;
    }


    public  static <T> Result<T> success(T data,String port){
        Result result = new Result<>();
        result.setCode(ReturnCode.RC200.getCode());
        result.setMessage(ReturnCode.RC200.getMessage()+"我是"+port);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String code,String message){
        Result result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


    public static <T> Result<T> fail(){
        Result result = new Result<>();
        result.setCode(ReturnCode.RC500.getCode());
        result.setMessage(ReturnCode.RC500.getMessage());
        return result;
    }








}
