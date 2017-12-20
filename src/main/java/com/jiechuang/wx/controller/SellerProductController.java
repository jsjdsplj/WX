package com.jiechuang.wx.controller;

import com.jiechuang.wx.dataobject.ProductCategory;
import com.jiechuang.wx.dataobject.ProductInfo;
import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.form.ProductForm;
import com.jiechuang.wx.service.CategoryService;
import com.jiechuang.wx.service.OrderService;
import com.jiechuang.wx.service.ProductService;
import com.jiechuang.wx.service.impl.OrderServiceImpl;
import com.jiechuang.wx.util.KeyUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.SunHints;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: lijie
 * @Date: 12:17 2017/11/20
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage=productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("productlist",map);
    }

     @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/wxsell/seller/product/list");
            return new ModelAndView("error",map);
        }

        map.put("msg","商品已上架");
        map.put("url","/wxsell/seller/product/list");
        return new ModelAndView("success",map);
     }


    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/wxsell/seller/product/list");
            return new ModelAndView("error",map);
        }

        map.put("msg","商品已下架");
        map.put("url","/wxsell/seller/product/list");
        return new ModelAndView("success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                       Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo=productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有类目
        List<ProductCategory> caategoryList=categoryService.findAll();
        map.put("categoryList",caategoryList);
         return new ModelAndView("productIndex",map);

    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/wxsell/seller/product/index");
            return new ModelAndView("error",map);
        }
        ProductInfo productInfo=new ProductInfo();
        try {
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo=productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
            map.put("url","/wxsell/seller/product/index?productId=" +productInfo.getProductId());
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/wxsell/seller/product/error");
            return new ModelAndView("error",map);
        }

        map.put("msg","提交成功");

        return new ModelAndView("success",map);
    }
}
