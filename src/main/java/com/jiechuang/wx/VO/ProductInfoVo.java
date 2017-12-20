package com.jiechuang.wx.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.math.BigDecimal;

/**
 * @Author: lijie
 * @Date: 12:13 2017/11/4
 */
@Data
public class ProductInfoVo {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;


}
