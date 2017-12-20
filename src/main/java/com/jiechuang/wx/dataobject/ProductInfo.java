package com.jiechuang.wx.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jiechuang.wx.enums.ProductStatusEnum;
import com.jiechuang.wx.util.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: lijie
 * @Date: 10:14 2017/11/4
 */

@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;
    /**
     *0正常1下架
     */
    private Integer productStatus=ProductStatusEnum.UP.getCode();

    private Date updateTime;

    private Integer categoryType;

    @JsonIgnore
    public   ProductStatusEnum getProductStatusEnum(){
       return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
   }

}
