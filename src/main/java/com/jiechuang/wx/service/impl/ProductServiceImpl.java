package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dao.ProductInfodao;
import com.jiechuang.wx.dataobject.ProductInfo;
import com.jiechuang.wx.dto.CartDTO;
import com.jiechuang.wx.enums.ProductStatusEnum;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author: lijie
 * @Date: 10:41 2017/11/4
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfodao productInfodao;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfodao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfodao.findByProductStatus(0);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfodao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfodao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
         for(CartDTO cartDTO:cartDTOList){
             ProductInfo productInfo=productInfodao.findOne(cartDTO.getProductId());
             if(productInfo==null){
                 throw new SellException(ResulEnum.product_not_exist);
             }

             Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
             productInfo.setProductStock(result);
             productInfodao.save(productInfo);
         }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
       for(CartDTO cartDTO:cartDTOList){
           ProductInfo productInfo=productInfodao.findOne(cartDTO.getProductId());
           if(productInfo==null){
               throw new SellException(ResulEnum.product_not_exist);
           }

           Integer result =productInfo.getProductStock()-cartDTO.getProductQuantity();
           if(result<0){
               throw new SellException(ResulEnum.PRODUCT_STOCK_ERROR);
           }

           productInfo.setProductStock(result);

           productInfodao.save(productInfo);
       }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=productInfodao.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResulEnum.product_not_exist);
        }
        if(productInfo.getProductStatusEnum()== ProductStatusEnum.UP){
            throw new SellException(ResulEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfodao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=productInfodao.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResulEnum.product_not_exist);
        }
        if(productInfo.getProductStatusEnum()== ProductStatusEnum.DOWN){
            throw new SellException(ResulEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfodao.save(productInfo);
    }
}
