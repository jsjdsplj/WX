package com.jiechuang.wx.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: lijie
 * @Date: 15:18 2017/11/21
 */
@Data
@Entity
public class SellerInfo
{
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

}
