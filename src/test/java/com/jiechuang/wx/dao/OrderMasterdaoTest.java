package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 19:59 2017/11/4
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterdaoTest {

    @Autowired
    private OrderMasterdao orderMasterdao;

    private final String OPENID="1000001";
    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("李杰");
        orderMaster.setBuyerPhone("17858905087");
        orderMaster.setBuyerAddress("杰创");
        orderMaster.setOrderAmount(new BigDecimal(18.2));
        orderMaster.setBuyerOpenid(OPENID);
        OrderMaster result=orderMasterdao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {

        PageRequest request=new PageRequest(3,3);

        Page<OrderMaster> result=orderMasterdao.findByBuyerOpenid(OPENID,request);

    }

}