package com.jiechuang.wx.enums;

import lombok.Getter;

/**
 * @Author: lijie
 * @Date: 19:45 2017/11/4
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
