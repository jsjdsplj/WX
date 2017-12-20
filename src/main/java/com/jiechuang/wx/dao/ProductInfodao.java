package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: lijie
 * @Date: 10:19 2017/11/4
 */
public interface ProductInfodao extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);


}
