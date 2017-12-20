package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dao.SellerInfodao;
import com.jiechuang.wx.dataobject.SellerInfo;
import com.jiechuang.wx.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lijie
 * @Date: 15:35 2017/11/21
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfodao sellerInfodao;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfodao.findByOpenid(openid);
    }
}
