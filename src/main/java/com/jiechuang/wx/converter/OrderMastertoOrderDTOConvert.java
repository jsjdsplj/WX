package com.jiechuang.wx.converter;

import com.jiechuang.wx.dataobject.OrderMaster;
import com.jiechuang.wx.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lijie
 * @Date: 16:36 2017/11/5
 */
public class OrderMastertoOrderDTOConvert {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
