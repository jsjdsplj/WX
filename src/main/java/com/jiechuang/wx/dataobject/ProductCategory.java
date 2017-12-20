package com.jiechuang.wx.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType, Date updateTime) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.updateTime = updateTime;
    }
}
