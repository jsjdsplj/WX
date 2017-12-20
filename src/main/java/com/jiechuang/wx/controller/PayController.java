/*package com.jiechuang.wx.controller;

import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.service.OrderService;
import com.jiechuang.wx.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
/**
 * @Author: lijie
 * @Date: 10:55 2017/11/10
 */
/*
@Controller
@RequestMapping("/pay")
public class PayController {

@Autowired
    private OrderService orderService;
@Autowired
    private PayService payService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                                            Map<String,Object> map){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO ==null){
            throw new SellException(ResulEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
        PayResponse payResponse=payService.create(orderDTO);
              map.put("orderId",payResponse);
              map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")
    public void notify(@RequestBody String notifyData){
        payService.notify(notifyData);
    }
}*/
