package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @Author: lijie
 * @Date: 15:20 2017/11/21
 */
public interface SellerInfodao extends JpaRepository<SellerInfo,Integer> {

    SellerInfo findByOpenid(String openid);
}
