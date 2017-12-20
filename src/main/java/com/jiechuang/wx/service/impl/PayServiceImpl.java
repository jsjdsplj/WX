/*package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.service.OrderService;
import com.jiechuang.wx.service.PayService;
import com.jiechuang.wx.util.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lijie
 * @Date: 10:13 2017/11/11
 */
//@Service
/*@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME="微信点餐系统";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderServiceImpl orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
         PayRequest payRequest=new PayRequest();
         payRequest.setOpenid(orderDTO.getBuyerOpenid());
         payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
         payRequest.setOrderName(ORDER_NAME);
         payRequest.setOrderId(orderDTO.getOrderId());
         payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
         log.info("【微信支付】request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse=bestPayService.pay(payRequest);
         log.info("【微信支付】response={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人==支付人）

        PayResponse payResponse=bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}",JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO=orderService.findOne(payResponse.getOrderId());
        if(orderDTO==null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResulEnum.ORDER_NOT_EXIST);
        }
        //修改支付状态
        if(!orderDTO.getOrderAmount().equals(payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，订单不存在，orderId={},微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResulEnum.WECHAT_NOTIFY_MONEY_VERIFY_ERROR);


        //修改支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }
}
*/