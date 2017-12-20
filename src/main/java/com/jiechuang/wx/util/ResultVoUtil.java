package com.jiechuang.wx.util;

import com.jiechuang.wx.VO.ResultVO;

/**
 * @Author: lijie
 * @Date: 17:57 2017/11/4
 */
public class ResultVoUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return  resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;

    }
}
