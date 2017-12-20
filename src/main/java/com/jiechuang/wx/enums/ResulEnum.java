package com.jiechuang.wx.enums;

import lombok.Getter;

/**
 * @Author: lijie
 * @Date: 12:45 2017/11/5
 */
@Getter
public enum  ResulEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    product_not_exist(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不存在"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIT(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WX_MP_ERROR(20,"微信公众账号错误"),

    WECHAT_NOTIFY_MONEY_VERIFY_ERROR(21,"微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22,"订单取消成功"),

    ORDER_FINISH_SUCCESS(23,"订单完结成功"),

    PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

    LOGIN_FAIL(25,"登入失败，登入信息不正确"),

    LOGOUT_SUCCESS(26,"推出成功"),
    ;
    private Integer code;

    private String message;

    ResulEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
