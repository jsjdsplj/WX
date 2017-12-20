package com.jiechuang.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lijie
 * @Date: 12:00 2017/11/10
 */
/*@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(){
        log.info("进入auth方法。。。。");

        String url="httpS://api.weixin.qq.com.sns/ouadd";
        RestTemplate restTemplate= new RestTemplate();
        String response= restTemplate.getForObject(url,String.class);
        log.info("response={}",response);
    }
}*/