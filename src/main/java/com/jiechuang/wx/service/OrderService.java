package com.jiechuang.wx.service;

import com.jiechuang.wx.dataobject.OrderMaster;
import com.jiechuang.wx.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: lijie
 * @Date: 12:23 2017/11/5
 */

public interface OrderService {

    /** 创建订单 */

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findOne(String orderId);

    /** 查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);

    Page<OrderDTO> findList(Pageable pageable);


}
