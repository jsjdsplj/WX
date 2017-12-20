package com.jiechuang.wx.exception;

import com.jiechuang.wx.enums.ResulEnum;
import lombok.Getter;

/**
 * @Author: lijie
 * @Date: 12:44 2017/11/5
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;


    public SellException(ResulEnum resulEnum){

        super(resulEnum.getMessage());

        this.code=resulEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
