package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: lijie
 * @Date: 19:52 2017/11/4
 */
public interface OrderMasterdao extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
