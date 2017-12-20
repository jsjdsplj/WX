package com.jiechuang.wx.service;

import com.jiechuang.wx.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

/**
 * @Author: lijie
 * @Date: 10:12 2017/11/11
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);
}
