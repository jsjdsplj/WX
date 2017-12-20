package com.jiechuang.wx.service;

import com.jiechuang.wx.dataobject.SellerInfo;

/**
 * @Author: lijie
 * @Date: 15:33 2017/11/21
 */
public interface SellerService {

    //通过openid查询卖家信息
    SellerInfo findSellerInfoByOpenid(String openid);
}
