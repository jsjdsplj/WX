package com.jiechuang.wx.VO;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * @Author: lijie
 * @Date: 12:00 2017/11/4
 */
@Data
public class ResultVO<T> {


private Integer code;

private String msg;

private T  data;



}
