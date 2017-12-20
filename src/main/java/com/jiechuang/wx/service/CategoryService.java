package com.jiechuang.wx.service;

import com.jiechuang.wx.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<ProductCategory>  findAll();

    ProductCategory save(ProductCategory productCategory);
}
