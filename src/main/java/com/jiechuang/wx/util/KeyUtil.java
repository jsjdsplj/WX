package com.jiechuang.wx.util;

import java.util.Random;

/**
 * @Author: lijie
 * @Date: 12:58 2017/11/5
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
