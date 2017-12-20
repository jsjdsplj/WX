package com.jiechuang.wx.dataobject.mapper;

import com.jiechuang.wx.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lijie
 * @Date: 15:22 2017/11/26
 */
public interface ProductCategoryMapper {

        @Insert("inert into product categoty(category name,category type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
        int insertByMap(Map<String,Object> map);


        @Insert("inert into product categoty(category name,category type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
        int insertByObject(ProductCategory productCategory);

        @Select("select *from product_category where category_type=#{categoryType}")
        @Results({
                @Result(column = "category_id",property = "categoryId"),
                @Result(column = "category_name",property = "categoryName"),
                @Result(column = "category_type",property = "categoryType")
        })
        ProductCategory findByCategoryType(Integer categoryType);


        @Select("select *from product_category where category_name=#{categoryType}")
        @Results({
                @Result(column = "category_id",property = "categoryId"),
                @Result(column = "category_name",property = "categoryName"),
                @Result(column = "category_type",property = "categoryType")
        })
        List<ProductCategory> findByCategoryName(Integer categoryType);


        @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
        int updateByCategorytype(@Param("categoryName") String categoryName,
                                 @Param("categoryType") Integer categoryType);


        @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
        int updateByObject(ProductCategory productCategory);


        @Delete("delete from product_category where category_type=#{categoryType}")
        int deleteByCategiryType(Integer categoryType);



        //使用mapper.xml的方式进行配置
        ProductCategory selectByCategoryTyoe(Integer categoryType);



}


