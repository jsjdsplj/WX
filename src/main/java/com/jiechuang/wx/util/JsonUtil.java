package com.jiechuang.wx.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author: lijie
 * @Date: 10:20 2017/11/15
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
