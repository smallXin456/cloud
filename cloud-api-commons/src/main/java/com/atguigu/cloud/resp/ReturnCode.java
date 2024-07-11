package com.atguigu.cloud.resp;


import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCode {
    //1.举值、2.构造、3.遍历


    //1.
    RC200("200","success"),
    RC500("500","系统异常");


    //2.
    private  String code;
    private  String message;

    ReturnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


//    3.1
    public ReturnCode getReturnCode(String code){

        for (ReturnCode element : ReturnCode.values()) {
            if(element.getCode().equalsIgnoreCase(code)){
                return element;
            }
        }
        return null;
    }

//3.2

    public ReturnCode getReturnCode2(String code){
        return Arrays.stream(ReturnCode.values())
                .filter(x -> x.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }


}
