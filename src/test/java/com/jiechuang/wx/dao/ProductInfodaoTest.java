package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.ProductInfo;
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
 * @Date: 10:21 2017/11/4
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfodaoTest {

@Autowired
private  ProductInfodao productInfodao;


    @Test
    public void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1234566");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://bianmintaosan");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo result =productInfodao.save(productInfo);

        Assert.assertNotNull(result);
    }



    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfoList=productInfodao.findByProductStatus(0);
        System.out.println(productInfoList);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}
