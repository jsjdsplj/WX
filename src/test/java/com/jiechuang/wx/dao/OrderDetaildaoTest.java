package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.OrderDetail;
import com.jiechuang.wx.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 20:23 2017/11/4
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetaildaoTest {

    @Autowired
    private OrderDetaildao orderDetaildao;
    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1234569");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("htt[://jiechuang.com");
        orderDetail.setProductId("12345");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(10.2));
        orderDetail.setProductQuantity(2);
        orderDetaildao.save(orderDetail);

    }


    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList=orderDetaildao.findByOrderId("1234567");

        System.out.println(orderDetailList);
        Assert.assertNotNull(orderDetailList);
    }

}