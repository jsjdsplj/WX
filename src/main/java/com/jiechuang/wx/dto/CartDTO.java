package com.jiechuang.wx.dto;

import lombok.Data;

/**
 * @Author: lijie
 * @Date: 13:12 2017/11/5
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
