package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.service.BuyerService;
import com.jiechuang.wx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lijie
 * @Date: 9:53 2017/11/6
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {

        return checkOrderOwner(openId,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO=checkOrderOwner(openId,orderId);
        if(orderDTO==null){
            log.error("【取消订单】查不到该订单，orderid={}",orderId);
            throw new SellException(ResulEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId,String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("【查询订单】订单的openid不一致,openid={},orderDTO={] ",openId,orderId);
            throw new SellException(ResulEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
