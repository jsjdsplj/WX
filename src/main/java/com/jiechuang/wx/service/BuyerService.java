package com.jiechuang.wx.service;

import com.jiechuang.wx.dto.OrderDTO;

/**
 * @Author: lijie
 * @Date: 9:51 2017/11/6
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openId,String orderId);

    OrderDTO cancelOrder(String openId,String orderId);


}
