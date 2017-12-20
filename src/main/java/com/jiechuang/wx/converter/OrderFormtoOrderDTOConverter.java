package com.jiechuang.wx.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiechuang.wx.dataobject.OrderDetail;
import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.form.OrderForm;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

/**
 * @Author: lijie
 * @Date: 20:52 2017/11/5
 */
@Slf4j
public class OrderFormtoOrderDTOConverter {


    public static  OrderDTO convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());


        List<OrderDetail> orderDetailList;


        try {

            orderDetailList=gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResulEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
