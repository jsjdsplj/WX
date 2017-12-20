package com.jiechuang.wx.util;

import com.jiechuang.wx.enums.CodeEnum;

/**
 * @Author: lijie
 * @Date: 22:46 2017/11/18
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
