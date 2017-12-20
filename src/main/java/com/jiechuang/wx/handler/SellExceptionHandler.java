package com.jiechuang.wx.handler;

import com.jiechuang.wx.VO.ResultVO;
import com.jiechuang.wx.config.ProjectUrlConfig;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.exception.SellerAuthorizeException;
import com.jiechuang.wx.util.ResultVoUtil;
import com.lly835.bestpay.rest.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: lijie
 * @Date: 14:56 2017/11/26
 */

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWechatMpAuthorize())
        .concat("/sell/wechat/qrAuthorize")
        .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login"));
    }


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }
}
