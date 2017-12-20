package com.jiechuang.wx.controller;

import com.jiechuang.wx.VO.ProductInfoVo;
import com.jiechuang.wx.VO.ProductVO;
import com.jiechuang.wx.VO.ResultVO;
import com.jiechuang.wx.dataobject.ProductCategory;
import com.jiechuang.wx.dataobject.ProductInfo;
import com.jiechuang.wx.service.CategoryService;
import com.jiechuang.wx.service.ProductService;
import com.jiechuang.wx.util.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lijie
 * @Date: 11:19 2017/11/4
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //1.查询所有
    @GetMapping("/list")
    public ResultVO list(){


        //1.查询所有的上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //2.查询类目
        List<Integer> categoryTypeList=new ArrayList<>();
        for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼接
        List<ProductVO> productVOList =new ArrayList<>();
        for(ProductCategory productCategory: productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVO.setProductInfoVoList(productInfoVoList);
            productVOList.add(productVO);
        }
        return ResultVoUtil.success(productVOList);
    }
}
