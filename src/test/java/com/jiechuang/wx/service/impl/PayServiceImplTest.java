package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dto.OrderDTO;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 10:30 2017/11/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {


    @Test
    public void create() throws Exception {
        PayRequest payRequest=new PayRequest();
      //  bestPayService.pay(payRequest);
    }


    @Test
    public void cres(){
        log.error("d");
    }

}