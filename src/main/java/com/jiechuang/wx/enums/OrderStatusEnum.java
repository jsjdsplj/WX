package com.jiechuang.wx.enums;

import lombok.Getter;

/**
 * @Author: lijie
 * @Date: 19:39 2017/11/4
 */
@Getter
public enum  OrderStatusEnum implements CodeEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
            ;


    private Integer code;

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }


}
