package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: lijie
 * @Date: 19:54 2017/11/4
 */
public interface OrderDetaildao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);

}
