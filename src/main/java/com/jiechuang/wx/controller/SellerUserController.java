package com.jiechuang.wx.controller;

import com.jiechuang.wx.config.ProjectUrlConfig;
import com.jiechuang.wx.constant.CookieConstant;
import com.jiechuang.wx.constant.RedisConstant;
import com.jiechuang.wx.dataobject.SellerInfo;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.service.SellerService;
import com.jiechuang.wx.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lijie
 * @Date: 17:06 2017/11/21
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                      HttpServletResponse response,
                      Map<String,Object> map){
        //1.openid去和数据库里的数据匹配

        SellerInfo sellerInfo=sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo==null){
            map.put("msg",ResulEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("error");
        }
        //2.设置tonken至redis

        String token= UUID.randomUUID().toString();
        Integer expire= RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);
        ///3.设置tonken至cookie

        CookieUtil.set(response,CookieConstant.TOKEN,token,expire);
        return new  ModelAndView("redirect:"+projectUrlConfig.getSell()+"/wxsell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){
        //从cookie里查询
        Cookie cookie=CookieUtil.get(request, CookieConstant.TOKEN);
            if(cookie!=null){
                //2.清除redis
                redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
                //3.清除cookie
                CookieUtil.set(response,CookieConstant.TOKEN,null,0);
            }
          map.put("msg",ResulEnum.LOGOUT_SUCCESS.getMessage());
          map.put("url","/wxsell/seller/order/list");
          return new ModelAndView("success",map);
    }

}
