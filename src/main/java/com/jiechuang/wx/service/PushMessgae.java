package com.jiechuang.wx.service;

import com.jiechuang.wx.dto.OrderDTO;

/**
 * @Author: lijie
 * @Date: 15:01 2017/11/23
 */
public interface PushMessgae {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
