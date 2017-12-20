package com.jiechuang.wx.form;

import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: lijie
 * @Date: 16:15 2017/11/20
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;
    /**
     *0正常1下架
     */
    private Date updateTime;

    private Integer categoryType;

}
