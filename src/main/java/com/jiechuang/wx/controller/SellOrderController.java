package com.jiechuang.wx.controller;

import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.Map;

/**
 * @Author: lijie
 * @Date: 19:10 2017/11/18
 */

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController  {

    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                              Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
       return new ModelAndView("list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
        Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
                log.error("【卖家端取消订单】发生异常{}",e);
                map.put("msg", e.getMessage());
                map.put("url","/wxsell/seller/order/list");
                return new ModelAndView("error",map);
        }
        map.put("msg",ResulEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/wxsell/seller/order/list");
       return new ModelAndView("success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
        Map<String,Object> map){
    OrderDTO orderDTO=new OrderDTO();
    try {
        orderDTO=orderService.findOne(orderId);
    }catch (SellException e){
        log.error("【卖家端查询订单详情】发生异常{}",e);
        map.put("msg",e.getMessage());
        map.put("url","/wxsell/seller/order/list");
        return new ModelAndView("error",map);
    }

    map.put("orderDTO",orderDTO);
    return new ModelAndView("detail",map);

    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
           OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/wxsell/seller/order/list");
            return new ModelAndView("error",map);
        }
        map.put("msg",ResulEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/wxsell/seller/order/list");
        return new ModelAndView("success",map);
    }

}
