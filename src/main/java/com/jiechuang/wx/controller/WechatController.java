package com.jiechuang.wx.controller;


import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/*
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

   @Autowired
   private WxMpService wxMpService;

   @Autowired
   private WxMpService wxOpenService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){

        //1.配置
        String url="外网地址/userInfo";
       String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userIfo(@RequestParam("code") String code,
                        @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResulEnum.WX_MP_ERROR.getCode(),e.getMessage());
        }
       String oppenId= wxMpOAuth2AccessToken.getOpenId();

        return "redirect:"+returnUrl+"?openid="+oppenId;
    }

    @GetMapping("/qrAuthorize")
    public String qeAuthorize(@RequestParam("retrunUrl") String returnUrl){
        String url="外网地址/qrUserInfo";
        String redirectUrl=wxOpenService.buildQrConnectUrl(url,WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN,returnUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String retrunUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken=wxOpenService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw  new SellException(ResulEnum.WX_MP_ERROR.getCode(),e.getMessage());
        }
        log.info("wxMpOAuth2AccessToken={}",wxMpOAuth2AccessToken);
        String openId=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + retrunUrl+"?openid="+openId;
    }
}*/
