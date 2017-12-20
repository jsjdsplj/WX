package com.jiechuang.wx.dao;

import com.jiechuang.wx.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategorydaoTest {

    @Autowired
    private ProductCategorydao productCategorydao;

    @Test
    public void findOneTest(){
        ProductCategory productCategory=productCategorydao.findOne(4);
        System.out.println(productCategory.toString());
    }


    @Test
    public void insert(){

        ProductCategory productCategory=new ProductCategory("女生",new Integer(3),new Date());


        productCategory=productCategorydao.save(productCategory);
        Assert.assertNotNull(productCategory);

    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(3,4);
        List<ProductCategory> productCategoryList=productCategorydao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategoryList.size());
        System.out.println(productCategoryList);
    }
}