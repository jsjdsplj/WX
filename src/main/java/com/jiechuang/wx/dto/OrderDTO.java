package com.jiechuang.wx.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiechuang.wx.dataobject.OrderDetail;
import com.jiechuang.wx.enums.OrderStatusEnum;
import com.jiechuang.wx.enums.PayStatusEnum;
import com.jiechuang.wx.util.EnumUtil;
import com.jiechuang.wx.util.serializer.DatetoLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lijie
 * @Date: 12:28 2017/11/5
 */

@Data

public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    @JsonSerialize(using = DatetoLongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
         return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

}
