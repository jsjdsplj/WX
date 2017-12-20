package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dataobject.OrderDetail;
import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.OrderStatusEnum;
import com.jiechuang.wx.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 13:37 2017/11/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Test
    public void findList1() throws Exception {
        PageRequest request=new PageRequest(0,3);
       Page<OrderDTO> orderDTOPage= orderService.findList(request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void create() throws Exception {
        OrderDTO orderDTO =new OrderDTO();
        orderDTO.setBuyerAddress("杰创大厦");
        orderDTO.setBuyerName("洪哥");
        orderDTO.setBuyerPhone("18767872963");
        orderDTO.setBuyerOpenid("112255");

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail o1=new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result=orderService.create(orderDTO);
        log.info("【创建订单】 result={}" ,result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {

        OrderDTO orderDTO=orderService.findOne("1509861657364416895");
        log.info("【查询单个订单】result={}",orderDTO );
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() throws Exception {
        PageRequest request=new PageRequest(0,3);
        Page<OrderDTO> orderDTOPage=orderService.findList("112233",request);
        log.info("【查询对应客户订单】result={}",orderDTOPage.getContent());
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() throws Exception {

     OrderDTO orderDTO=orderService.findOne("1509861838388622593");
     OrderDTO result=orderService.cancel(orderDTO);
     Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() throws Exception {

        OrderDTO orderDTO=orderService.findOne("1509861681827182396");
        OrderDTO result=orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());

    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO=orderService.findOne("1509861309952573446");
        OrderDTO result=orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }



}