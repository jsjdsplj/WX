package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.service.PushMessgae;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: lijie
 * @Date: 15:01 2017/11/23
 */
/*@Service
@Slf4j
public class PushMessageImpl implements PushMessgae {

    @Autowired
    private  WxMpServiceImpl wxMpService;


    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage=new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId("");
        wxMpTemplateMessage.setToUser("");
        List<WxMpTemplateData> data= Arrays.asList(new WxMpTemplateData("first","记得收获"),
                                                   new WxMpTemplateData("keyword1","微信点餐"),
                                                   new WxMpTemplateData("keyword2","1886446565"),
                                                   new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                                                   new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
                                                           new WxMpTemplateData("keyword5","$"+orderDTO.getOrderAmount()),
                                                   new WxMpTemplateData("remark","欢迎光临")
                                                           ){
            wxMpTemplateMessage.setData(data);
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            }catch (WxErrorException e){
                log.error("【微信模板消息】发送失败，{}",e);
            }
        }
    }
}*/
