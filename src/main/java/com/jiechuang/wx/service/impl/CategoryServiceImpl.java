package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dao.ProductCategorydao;
import com.jiechuang.wx.dataobject.ProductCategory;
import com.jiechuang.wx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategorydao productCategorydao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategorydao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategorydao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategorydao.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategorydao.save(productCategory);
    }
}
