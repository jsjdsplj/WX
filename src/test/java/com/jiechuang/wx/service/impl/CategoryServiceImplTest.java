package com.jiechuang.wx.service.impl;

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

import static org.junit.Assert.*;

/**
 * @Author: lijie
 * @Date: 21:27 2017/11/2
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private  CategoryServiceImpl categoryService;
    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory=categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(Arrays.asList(3,4));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList=categoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("变态", 18, new Date());
        categoryService.save(productCategory);

    }

}