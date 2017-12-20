package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.SellerInfo;
import com.jiechuang.wx.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 15:22 2017/11/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfodaoTest {

    @Autowired
    private SellerInfodao sellerInfodao;
    @Test
    public void findByOpenid() throws Exception {

        SellerInfo info=sellerInfodao.findByOpenid("abc");
       Assert.assertEquals("abc",info.getOpenid());
    }

    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setOpenid("abc");
        sellerInfo.setPassword("admin");
        sellerInfo.setUsername("admin");
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        SellerInfo result=sellerInfodao.save(sellerInfo);
        Assert.assertNotNull(result);
    }



}