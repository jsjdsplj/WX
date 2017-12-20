package com.jiechuang.wx.service.impl;

import com.jiechuang.wx.converter.OrderMastertoOrderDTOConvert;
import com.jiechuang.wx.dao.OrderDetaildao;
import com.jiechuang.wx.dao.OrderMasterdao;
import com.jiechuang.wx.dataobject.OrderDetail;
import com.jiechuang.wx.dataobject.OrderMaster;
import com.jiechuang.wx.dataobject.ProductInfo;
import com.jiechuang.wx.dto.CartDTO;
import com.jiechuang.wx.dto.OrderDTO;
import com.jiechuang.wx.enums.OrderStatusEnum;
import com.jiechuang.wx.enums.PayStatusEnum;
import com.jiechuang.wx.enums.ResulEnum;
import com.jiechuang.wx.exception.SellException;
import com.jiechuang.wx.service.OrderService;
import com.jiechuang.wx.service.ProductService;
import com.jiechuang.wx.service.WebSocket;
import com.jiechuang.wx.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: lijie
 * @Date: 12:33 2017/11/5
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{


    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetaildao orderDetaildao;

    @Autowired
    private OrderMasterdao orderMasterdao;

    @Autowired
    private WebSocket webSocket;

    //@Autowired
    //private PushMessageImpl pushMessage;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId=KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);


        //1.查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
           ProductInfo productInfo= productService.findOne(orderDetail.getProductId());
           if(productInfo==null){
                throw  new SellException(ResulEnum.product_not_exist);
           }

            //2.计算总价
            orderAmount=productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetaildao.save(orderDetail);
        }


        //3.订单所有入库（OrderMaster)
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterdao.save(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList= orderDTO.getOrderDetailList().stream().map(e ->
        new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        //发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster=orderMasterdao.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResulEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList=orderDetaildao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResulEnum.ORDERDETAIL_NOT_EXIT);
        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterdao.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList=OrderMastertoOrderDTOConvert.convert(orderMasterPage.getContent());

       return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster=new OrderMaster();

        //判断订单的状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResulEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterdao.save(orderMaster);
        if(updateResult==null){
            log.error("【取消订单】更新失败，oderMaster={}",orderMaster);
            throw new SellException(ResulEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】无商品详情，odderDTO={}",orderDTO);
            throw new SellException(ResulEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付则退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }


        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getPayStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResulEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterdao.save(orderMaster);
        if(updateResult==null){
            log.error("【完结订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResulEnum.ORDER_UPDATE_FAIL);
        }

        //这里的异常直接被捕捉了，不被抛出，不会回滚
       // pushMessage.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
         if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
             log.error("【订单支付】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
             throw new SellException(ResulEnum.ORDER_STATUS_ERROR);
         }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】订单支付状态不正确，orderDTO={}",orderDTO);
            throw new SellException(ResulEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterdao.save(orderMaster);
        if(updateResult==null){
            log.error("【订单支付】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResulEnum.ORDER_UPDATE_FAIL);
        }


        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterdao.findAll(pageable);
        List<OrderDTO> orderDTOList=OrderMastertoOrderDTOConvert.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }
}
