package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 15:36 2017/11/21
 */

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {


    @Autowired
    private SellerServiceImpl sellerService;
    @Test
    public void findSellerInfoByOpenid() throws Exception {

       SellerInfo sellerInfo= sellerService.findSellerInfoByOpenid("abc");
        log.info("sellerInfo={}" ,sellerInfo);
    }

}